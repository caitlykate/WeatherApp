package com.example.weatherapp.data.api.forecastEntities

import com.google.gson.annotations.SerializedName

data class ForecastWeatherResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    @SerializedName("list")
    val weatherList: List<CityWeatherForecast>,
    val message: Int
)