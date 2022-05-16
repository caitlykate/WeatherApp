package com.example.weatherapp.domain.entities

data class ForecastWeather(
    val date: Long,
    val temp: Int,
    val feelsLike: Int,
    val tempMin: Int,
    val tempMax: Int,
    val windSpeed: Int,
    val icon: String
)
