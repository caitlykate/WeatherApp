package com.example.weatherapp.domain

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.Api
import com.example.weatherapp.data.dto.CityWeatherFull
import io.reactivex.rxjava3.core.Observable

private const val METRIC = "metric"
private const val CITY_ID_LIST = "536203,524894,5128581,2643743,2968815"

interface GetWeatherInteractor {

    fun getWeather(): Observable<List<CityWeather>>
}

//typealias CityWeather = CityWeatherResponse

class GetWeatherInteractorImpl(
    private val api: Api,
    private val mapper: (CityWeatherFull) -> CityWeather,
) : GetWeatherInteractor {

    override fun getWeather(): Observable<List<CityWeather>> {
        return api.getWeather(CITY_ID_LIST, METRIC, BuildConfig.API_KEY)
            .map { cityWeatherResponse ->
                cityWeatherResponse.weatherList.map { weather ->
                    mapper(weather)
                }
            }
    }
}
