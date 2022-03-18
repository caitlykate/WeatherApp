package com.example.weatherapp.domain.entities

data class CityWeather(
    val id: Int,
    val cityName: String,
    val temp: Int,
    val feelsLike: Int
)
