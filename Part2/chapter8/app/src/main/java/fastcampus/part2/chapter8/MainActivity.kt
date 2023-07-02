package fastcampus.part2.chapter8

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.naver.maps.map.*
import com.naver.maps.map.util.FusedLocationSource
import fastcampus.part2.chapter8.databinding.ActivityMainBinding
import android.widget.Toast
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED
import com.naver.maps.geometry.LatLng
import com.naver.maps.geometry.Tm128
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import fastcampus.part2.chapter8.adapter.RestaurantListAdapter
import fastcampus.part2.chapter8.search.SearchRepository
import fastcampus.part2.chapter8.search.SearchResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMainBinding
    private lateinit var naverMap: NaverMap
    private lateinit var locationSource: FusedLocationSource

    private var isMapInit = false
    private var markers = emptyList<Marker>()

    private var restaurantListAdapter = RestaurantListAdapter {
        collapseBottomSheet()
        moveCamera(it, 17.0)
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync(this)

        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)

        binding.bottomSheetLayout.searchResultRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = restaurantListAdapter
        }

        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return if (query?.isNotEmpty() == true) {
                    SearchRepository.getGoodRestaurant(query)
                        .enqueue(object : Callback<SearchResult> {
                            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                                val searchItemList = response.body()?.items.orEmpty()
                                if (searchItemList.isEmpty()) {
                                    Toast.makeText(this@MainActivity, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show()
                                    return
                                } else if (isMapInit.not()) {
                                    Toast.makeText(this@MainActivity, "오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
                                    return
                                }

                                markers.forEach {
                                    it.map = null
                                }

                                markers = searchItemList.map {
                                    Marker(
                                        Tm128(it.mapx.toDouble(), it.mapy.toDouble()).toLatLng()).apply {
                                        captionText = it.title
                                        map = naverMap
                                    }
                                }

                                restaurantListAdapter.setData(searchItemList)
                                collapseBottomSheet()
                                moveCamera(markers.first().position, 13.0)
                            }

                            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                                t.printStackTrace()
                            }
                        })
                    false
                } else {
                    true
                }
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun collapseBottomSheet() {
        val bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheetLayout.root)
        bottomSheetBehavior.state = STATE_COLLAPSED
    }

    private fun moveCamera(position: LatLng, zoomLevel: Double) {
        if (isMapInit.not())
            return

        val cameraUpdate = CameraUpdate.scrollAndZoomTo(position, zoomLevel)
            .animate(CameraAnimation.Easing)

        naverMap.moveCamera(cameraUpdate)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            if (!locationSource.isActivated) { // 권한 거부됨
                naverMap.locationTrackingMode = LocationTrackingMode.None
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onStop() {
        super.onStop()

        binding.mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()

        binding.mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        binding.mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()

        binding.mapView.onLowMemory()
    }

    override fun onMapReady(mapObject: NaverMap) {
        naverMap = mapObject
        naverMap.uiSettings.isLocationButtonEnabled = true
        mapObject.locationSource = locationSource
        isMapInit = true
    }
}