package com.example.weatherapp.domain

import com.example.weatherapp.data.Api
import com.example.weatherapp.data.CityWeatherResponse
import io.reactivex.rxjava3.core.Observable

interface GetWeatherInteractor {

    fun getWeather(): Observable<List<CityWeather>>
}

typealias CityWeather = CityWeatherResponse

class GetWeatherInteractorImpl(
    api: Api,
) : GetWeatherInteractor {

    override fun getWeather(): Observable<List<CityWeather>> {
        TODO("Not yet implemented")
    }

}
