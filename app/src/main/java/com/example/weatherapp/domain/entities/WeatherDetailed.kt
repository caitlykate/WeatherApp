package com.example.weatherapp.domain.entities

data class WeatherDetailed(
    val cityName: String,
    val date: Long,
    val temp: Int,
    val feelsLike: Int,
    val windSpeed: Int,
    val humidity: Int,
    val pressure: Int,
    val icon: String
)
