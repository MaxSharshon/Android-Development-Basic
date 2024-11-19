package com.example.android

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.android.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var temperatureText: TextView
    private lateinit var humidityText: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        temperatureText = findViewById(R.id.temperatureText)
        humidityText = findViewById(R.id.humidityText)

        fetchWeatherData("London")
    }

    private fun fetchWeatherData(city: String) {
        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    ApiClient.weatherService.getWeather(city, BuildConfig.OPENWEATHER_API_KEY)
                }
                temperatureText.text = "Temperature: ${response.main.temp}°C"
                humidityText.text = "Humidity: ${response.main.humidity}%"
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
