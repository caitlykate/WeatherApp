package com.example.weatherapp.data

import com.example.weatherapp.data.dto.CityWeatherResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

//https://api.openweathermap.org/data/2.5/group?id=536203,524894,5128581,2643743,2968815&appid=4d0a72df8fb92392761c2d9d14590056&units=metric
interface Api {

    @GET("data/2.5/group")    //?id=536203,524894,5128581,2643743,2968815&appid=4d0a72df8fb92392761c2d9d14590056&units=metric
    fun getWeather(
        @Query("id") id: String,
        @Query("units") units: String,
        @Query("appid") appid: String
    ): Observable<CityWeatherResponse>
}