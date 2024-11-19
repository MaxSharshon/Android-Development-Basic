package com.example.android.network

import com.squareup.moshi.Json

data class WeatherResponse (
    @field:Json(name = "main") val main: Main
)

data class Main(
    @field:Json(name = "temp") val temp: Double,
    @field:Json(name = "humidity") val humidity: Int
)