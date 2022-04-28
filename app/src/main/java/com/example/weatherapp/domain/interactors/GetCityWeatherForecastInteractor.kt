package com.example.weatherapp.domain.interactors

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.api.Api
import com.example.weatherapp.data.api.entities.CityWeatherFull
import com.example.weatherapp.data.api.forecastEntities.CityWeatherForecast
import com.example.weatherapp.data.db.MainDataBase
import com.example.weatherapp.data.db.entities.CityWeatherEntity
import com.example.weatherapp.domain.entities.CityWeather
import com.example.weatherapp.domain.entities.ForecastWeather
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

private const val METRIC = "metric"
private const val CITY_ID_LIST = "536203,524894,5128581,2643743,2968815"

interface GetCityWeatherForecastInteractor {

    fun getWeather(cityId: Int): Single<List<ForecastWeather>>
}

class GetCityWeatherForecastInteractorImpl(
    private val api: Api,
    private val forecastWeatherMapper : (CityWeatherForecast) -> ForecastWeather,
) : GetCityWeatherForecastInteractor {

    data class Optional<T>(val value: T?)
    override fun getWeather(cityId: Int): Single<List<ForecastWeather>> {

        return api
            .getForecastWeather(
                id = cityId.toString(),
                units = METRIC,
                appid = BuildConfig.API_KEY,
            )
            .map { forecastWeatherResponse ->
                forecastWeatherResponse.weatherList.map { cityWeatherForecast ->
                    forecastWeatherMapper(cityWeatherForecast)
                }
            }
    }
}

