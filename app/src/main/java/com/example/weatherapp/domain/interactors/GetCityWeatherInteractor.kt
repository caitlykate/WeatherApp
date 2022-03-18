package com.example.weatherapp.domain.interactors

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.api.Api
import com.example.weatherapp.data.db.entities.CityWeatherEntity
import com.example.weatherapp.domain.CITY_ID_LIST
import com.example.weatherapp.domain.METRIC
import com.example.weatherapp.domain.entities.CityWeather
import io.reactivex.rxjava3.core.Observable

interface GetCityWeatherInteractor {

    fun getWeather(): Observable<CityWeather>
}

//typealias CityWeather = CityWeatherResponse

class GetCityWeatherInteractorImpl(
    private val api: Api,
    private val cityWeatherEntityToCityWeatherMapper: (CityWeatherEntity) -> CityWeather,
) : GetCityWeatherInteractor {

    override fun getWeather(): Observable<CityWeather> {
        return api
            .getCurrentWeather(
                id = CITY_ID_LIST,
                units = METRIC,
                appid = BuildConfig.API_KEY,
            )
    }
}
