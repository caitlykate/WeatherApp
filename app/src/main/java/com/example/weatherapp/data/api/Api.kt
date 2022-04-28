package com.example.weatherapp.data.api

import com.example.weatherapp.data.api.entities.CityWeatherResponse
import com.example.weatherapp.data.api.forecastEntities.ForecastWeatherResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//https://api.openweathermap.org/data/2.5/group?id=536203,524894,5128581,2643743,2968815&appid=4d0a72df8fb92392761c2d9d14590056&units=metric
interface Api {

    @GET("data/2.5/group")    //?id=536203,524894,5128581,2643743,2968815&appid=4d0a72df8fb92392761c2d9d14590056&units=metric
    fun getCurrentWeather(
        @Query("id") id: String,
        @Query("units") units: String,
        @Query("appid") appid: String
    ): Single<CityWeatherResponse>

    //https://api.openweathermap.org/data/2.5/forecast?id=536203&appid=4d0a72df8fb92392761c2d9d14590056&units=metric
    //https://api.openweathermap.org/data/2.5/onecall?lat=59.9167&lon=30.25&exclude=hourly,daily&appid=4d0a72df8fb92392761c2d9d14590056

    @GET("data/2.5/forecast")    //?id=536203,524894,5128581,2643743,2968815&appid=4d0a72df8fb92392761c2d9d14590056&units=metric
    fun getForecastWeather(
        @Query("id") id: String,
        @Query("units") units: String,
        @Query("appid") appid: String
    ): Single<ForecastWeatherResponse>

    //https://openweathermap.org/img/wn/10n@4x.png
    @GET("img/wn/{imgName}")    //?id=536203,524894,5128581,2643743,2968815&appid=4d0a72df8fb92392761c2d9d14590056&units=metric
    fun getImg(
        @Path("imgName") imgName: String
    ): Single<ForecastWeatherResponse>
}