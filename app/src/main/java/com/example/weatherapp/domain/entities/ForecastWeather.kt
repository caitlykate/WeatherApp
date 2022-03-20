package com.example.weatherapp.domain.entities

data class ForecastWeather(
    val dt: Int,
    val temp: Int,
    val feelsLike: Int,
    val windSpeed: Int,
)
