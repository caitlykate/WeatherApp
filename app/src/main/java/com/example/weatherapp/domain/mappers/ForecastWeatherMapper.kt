package com.example.weatherapp.domain.mappers

import com.example.weatherapp.data.api.forecastEntities.CityWeatherForecast
import com.example.weatherapp.domain.entities.ForecastWeather
import kotlin.math.roundToInt

class ForecastWeatherMapper : (CityWeatherForecast) -> ForecastWeather {
    override fun invoke(cityWeatherForecast: CityWeatherForecast): ForecastWeather {
        return with(cityWeatherForecast){
            ForecastWeather(
                date = dt,
                temp = main.temp.roundToInt(),
                feelsLike = main.feels_like.roundToInt(),
                tempMin = main.temp_min.roundToInt(),
                tempMax = main.temp_max.roundToInt(),
                windSpeed = wind.speed.roundToInt(),
                icon = weather.first().icon
            )
        }
    }

}