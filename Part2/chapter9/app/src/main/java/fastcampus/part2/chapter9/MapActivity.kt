package fastcampus.part2.chapter9

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import fastcampus.part2.chapter9.databinding.ActivityMapBinding

class MapActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var binding: ActivityMapBinding
    private lateinit var googleMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var trackingPersonId: String = ""
    private val markerMap = hashMapOf<String, Marker>()

    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                // fine location 권한이 있음
                getCurrentLocation()
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                // coarse Location 권한이 있음
                getCurrentLocation()
            }
            else -> {
                // TODO 설정으로 보내기 or 교육용 팝업을 띄워서 다시 권한 요청하기
            }
        }
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            // 새로 요청된 위치 정보
            for (location in locationResult.locations) {
                val uid = Firebase.auth.currentUser?.uid.orEmpty()
                val locationMap = mutableMapOf<String, Any>()

                locationMap["latitude"] = location.latitude
                locationMap["longitude"] = location.longitude

                Firebase.database.reference.child("Person").child(uid).updateChildren(locationMap)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        requestLocationPermission()
        setupFirebaseDatabase()
        setupEmojiAnimationView()
        setupCurrentLocationView()
    }

    override fun onResume() {
        super.onResume()
        getCurrentLocation()
    }

    override fun onPause() {
        super.onPause()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    private fun getCurrentLocation() {
        val locationRequest = LocationRequest
            .Builder(Priority.PRIORITY_HIGH_ACCURACY, 5 * 1000)
            .build()

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestLocationPermission()
            return
        }

        // 권한이 있는 상태
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )

        moveLastLocation()
    }

    private fun requestLocationPermission() {
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    private fun moveLastLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestLocationPermission()
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener {
            googleMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(LatLng(it.latitude, it.longitude), 16.0f)
            )
        }
    }

    private fun setupFirebaseDatabase() {
        Firebase.database.reference.child("Person")
            .addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val person = snapshot.getValue(Person::class.java) ?: return
                    val uid = person.uid ?: return

                    if (markerMap[uid] == null) {
                        markerMap[uid] = makeNewMarker(person, uid) ?: return
                    }
                }

                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                    val person = snapshot.getValue(Person::class.java) ?: return
                    val uid = person.uid ?: return

                    if (markerMap[uid] == null) {
                        markerMap[uid] = makeNewMarker(person, uid) ?: return
                    } else {
                        markerMap[uid]?.position = LatLng(person.latitude ?: 0.0, person.longitude ?: 0.0)
                    }

                    if (uid == trackingPersonId) {
                        googleMap.animateCamera(
                            CameraUpdateFactory.newCameraPosition(
                                CameraPosition.Builder()
                                    .target(LatLng(person.latitude ?: 0.0, person.longitude ?: 0.0))
                                    .zoom(16.0f)
                                    .build()
                            )
                        )
                    }
                }

                override fun onChildRemoved(snapshot: DataSnapshot) {}

                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

                override fun onCancelled(error: DatabaseError) {}
            })

        Firebase.database.reference.child("Emoji").child(Firebase.auth.currentUser?.uid ?: "")
            .addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {}

                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                    binding.centerLottieAnimationView.playAnimation()
                    binding.centerLottieAnimationView.animate()
                        .scaleX(7f)
                        .scaleY(7f)
                        .alpha(0.3f)
                        .setDuration(binding.centerLottieAnimationView.duration / 3)
                        .withEndAction {
                            binding.centerLottieAnimationView.scaleX = 0f
                            binding.centerLottieAnimationView.scaleY = 0f
                            binding.centerLottieAnimationView.alpha = 1f
                        }.start()
                }

                override fun onChildRemoved(snapshot: DataSnapshot) {}

                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

                override fun onCancelled(error: DatabaseError) {}
            })
    }

    private fun makeNewMarker(person: Person, uid: String): Marker? {
        val marker = googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(person.latitude ?: 0.0, person.longitude ?: 0.0))
                .title(person.name.orEmpty())
        ) ?: return null

        marker.tag = uid

        Glide.with(this).asBitmap()
            .load(person.profilePhoto)
            .transform(RoundedCorners(60))
            .override(200)
            .listener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    resource?.let {
                        runOnUiThread {
                            marker.setIcon(
                                BitmapDescriptorFactory.fromBitmap(
                                    resource
                                )
                            )
                        }
                    }
                    return true
                }
            }).submit()

        return marker
    }

    private fun setupEmojiAnimationView() {
        binding.emojiLottieAnimationView.setOnClickListener {
            if (trackingPersonId != "") {
                val lastEmoji = mutableMapOf<String, Any>()
                lastEmoji["type"] = "star"
                lastEmoji["lastModifier"] = System.currentTimeMillis()
                Firebase.database.reference.child("Emoji").child(trackingPersonId).updateChildren(lastEmoji)
            }

            binding.emojiLottieAnimationView.playAnimation()

            binding.dummyLottieAnimationView.animate()
                .scaleX(3f)
                .scaleY(3f)
                .alpha(0f)
                .withStartAction {
                    binding.dummyLottieAnimationView.scaleX = 1f
                    binding.dummyLottieAnimationView.scaleY = 1f
                    binding.dummyLottieAnimationView.alpha = 1f
                }.withEndAction {
                    binding.dummyLottieAnimationView.scaleX = 1f
                    binding.dummyLottieAnimationView.scaleY = 1f
                    binding.dummyLottieAnimationView.alpha = 1f
                }.start()
        }

        binding.emojiLottieAnimationView.speed = 3f
        binding.centerLottieAnimationView.speed = 3f
    }

    private fun setupCurrentLocationView() {
        binding.btnCurrentLocation.setOnClickListener {
            trackingPersonId = ""
            moveLastLocation()
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        googleMap.setMaxZoomPreference(20.0f)
        googleMap.setMinZoomPreference(10.0f)

        googleMap.setOnMarkerClickListener(this)
        googleMap.setOnMapClickListener {
            trackingPersonId = ""
        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        trackingPersonId = marker.tag as? String ?: ""

        val bottomSheetBehavior = BottomSheetBehavior.from(binding.emojiBottomSheetLayout)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

        return false
    }
}