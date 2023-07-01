package fastcampus.part2.chapter7

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import fastcampus.part2.chapter7.databinding.ActivityMainBinding
import fastcampus.part2.chapter7.databinding.ItemForecastBinding
import fastcampus.part2.chapter7.weather.WeatherRepository
import retrofit2.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                updateLocation()
            }
            else -> {
                Toast.makeText(this, "위치 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", packageName, null)
                }
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        locationPermissionRequest.launch(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION))
    }

    @Suppress("DEPRECATION")
    private fun updateLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            locationPermissionRequest.launch(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION))
            return
        }

        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation.addOnSuccessListener {
            Thread {
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        Geocoder(this, Locale.KOREA).getFromLocation(
                            it.latitude,
                            it.longitude,
                            1
                        ) { addressList ->
                            runOnUiThread {
                                binding.tvLocation.text = addressList[0]?.thoroughfare.orEmpty()
                            }
                        }
                    } else {
                        val addressList = Geocoder(this, Locale.KOREA).getFromLocation(
                            it.latitude,
                            it.longitude,
                            1
                        )

                        runOnUiThread {
                            binding.tvLocation.text = addressList?.get(0)?.thoroughfare.orEmpty()
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }.start()

            WeatherRepository.getVillageForecast(
                longitude = it.longitude,
                latitude = it.latitude,
                successCallback = { list ->
                    val currentForecast = list.first()

                    binding.tvTemperature.text = getString(R.string.temperature_text, currentForecast.temperature)
                    binding.tvSky.text = currentForecast.weather
                    binding.tvPrecipitation.text = getString(R.string.precipitation_text, currentForecast.precipitation)
                    binding.childForecastLayout.apply {
                        list.forEachIndexed { index, forecast ->
                            if (index == 0) {
                                return@forEachIndexed
                            }

                            val itemView = ItemForecastBinding.inflate(layoutInflater)
                            itemView.tvTime.text = forecast.forecastTime
                            itemView.tvWeather.text = forecast.weather
                            itemView.tvTemperature.text = getString(R.string.temperature_text, forecast.temperature)

                            addView(itemView.root)
                        }
                    }
                },
                failureCallback = { it2 ->
                    it2.printStackTrace()
                }
            )
        }
    }
}