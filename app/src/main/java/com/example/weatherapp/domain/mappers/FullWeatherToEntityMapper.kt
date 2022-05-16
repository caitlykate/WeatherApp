package com.example.weatherapp.domain.mappers

import com.example.weatherapp.data.api.entities.CityWeatherData
import com.example.weatherapp.domain.entities.CityWeather
import kotlin.math.roundToInt

class FullWeatherToEntityMapper : (CityWeatherData) -> CityWeather {

    override fun invoke(weath: CityWeatherData): CityWeather {
        return CityWeather(
            weath.id,
            weath.name,
            weath.main.temp.roundToInt(),
            weath.main.feels_like.roundToInt()
        )
    }
}