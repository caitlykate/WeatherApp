package com.example.weatherapp.data.api.entities

import com.google.gson.annotations.SerializedName

data class CityWeatherResponse(
    val cnt: Int,

    @SerializedName("list")
    val weatherList: List<CityWeatherData>
)