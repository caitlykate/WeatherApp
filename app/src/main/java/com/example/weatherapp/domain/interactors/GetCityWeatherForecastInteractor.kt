package com.example.weatherapp.domain.interactors

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.api.Api
import com.example.weatherapp.data.api.forecastEntities.CityWeatherForecast
import com.example.weatherapp.domain.entities.ForecastWeather
import io.reactivex.rxjava3.core.Single

private const val METRIC = "metric"

interface GetCityWeatherForecastInteractor {

    fun getWeather(cityId: Int): Single<List<ForecastWeather>>
}

class GetCityWeatherForecastInteractorImpl(
    private val api: Api,
    private val forecastWeatherMapper : (CityWeatherForecast) -> ForecastWeather,
) : GetCityWeatherForecastInteractor {

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

