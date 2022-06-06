package com.example.weatherapp.domain.interactors

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.api.Api
import com.example.weatherapp.data.api.entities.CityWeatherData
import com.example.weatherapp.data.db.MainDataBase
import com.example.weatherapp.data.db.entities.CityWeatherEntity
import io.reactivex.rxjava3.core.Completable

private const val METRIC = "metric"
private const val CITY_ID_LIST = "536203,524894,5128581,2643743,2968815"

interface UpdateWeatherInteractor {

    fun updateWeather(): Completable
}

class UpdateWeatherInteractorImpl(
    private val api: Api,
    private val db: MainDataBase,
    private val weatherDomainMapper: (CityWeatherData) -> CityWeatherEntity
) : UpdateWeatherInteractor {

    override fun updateWeather(): Completable {
        return api
            .getCurrentWeather(
                id = CITY_ID_LIST,
                units = METRIC,
                appid = BuildConfig.API_KEY,
            )
            .doOnSuccess { cityWeatherResponse ->

                val cityWeatherEntities = cityWeatherResponse.weatherList.map { cityWeatherFull ->
                    weatherDomainMapper(cityWeatherFull)
                }

                db.getDao().insertCityWeather(
                    cityWeathers = cityWeatherEntities,
                )
            }
            .ignoreElement()
    }
}
