package com.example.weatherapp.data.api.forecastEntities

data class Main(
    val feels_like: Double,
    val grnd_level: Int,
    val humidity: Int,      //влажность
    val pressure: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
)