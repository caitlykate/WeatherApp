package com.example.weatherapp.domain.mappers

import com.example.weatherapp.data.api.forecastEntities.CityWeatherForecast
import com.example.weatherapp.domain.entities.WeatherDetailed

class CityWeatherForecastToWeatherDetailedMapper : (CityWeatherForecast) -> WeatherDetailed {
    override fun invoke(cityWeatherForecast: CityWeatherForecast): WeatherDetailed {
        return with(cityWeatherForecast) {
            WeatherDetailed(
                cityName = "Name",
                date = dt,
                temp = main.temp.toInt(),
                feelsLike = main.feels_like.toInt(),
                windSpeed = wind.speed.toInt(),
                humidity = main.humidity,
                pressure = main.pressure,
                icon = weather.first().icon
            )
        }
    }
}