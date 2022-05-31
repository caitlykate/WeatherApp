package com.example.weatherapp.data.api.entities

data class CityWeatherData(

    val dt: Long,
    val id: Int,
    val main: Main,
    val name: String,
)