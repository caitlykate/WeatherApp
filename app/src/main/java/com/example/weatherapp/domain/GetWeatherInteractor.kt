package com.example.weatherapp.domain

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.Api
import com.example.weatherapp.data.dto.CityWeatherResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

private const val METRIC = "metric"
private const val CITY_ID_LIST = "536203,524894,5128581,2643743,2968815"

interface GetWeatherInteractor {

    fun getWeather(): Observable<CityWeather>
}

typealias CityWeather = CityWeatherResponse

class GetWeatherInteractorImpl(
   val api: Api,
) : GetWeatherInteractor {

    override fun getWeather(): Observable<CityWeather> {
        val response = api.getWeather(CITY_ID_LIST, METRIC, BuildConfig.API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        return response
    }

}
