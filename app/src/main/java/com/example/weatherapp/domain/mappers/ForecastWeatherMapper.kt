package com.example.weatherapp.domain.mappers

import com.example.weatherapp.data.api.forecastEntities.CityWeatherForecast
import com.example.weatherapp.domain.entities.ForecastWeather
import kotlin.math.roundToInt

class ForecastWeatherMapper : (CityWeatherForecast) -> ForecastWeather {
    override fun invoke(cityWeatherForecast: CityWeatherForecast): ForecastWeather {
        return with(cityWeatherForecast){
            ForecastWeather(
                dt = dt,
                temp = main.temp.roundToInt(),
                feelsLike = main.feels_like.roundToInt(),
                windSpeed = wind.speed.roundToInt()
            )
        }
    }

}