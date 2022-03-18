package com.example.weatherapp.domain.mappers

import com.example.weatherapp.data.api.entities.CityWeatherFull
import com.example.weatherapp.domain.entities.CityWeather
import kotlin.math.roundToInt

class FullWeatherToEntityMapper : (CityWeatherFull) -> CityWeather {

    override fun invoke(weath: CityWeatherFull): CityWeather {
        return CityWeather(
            weath.id,
            weath.name,
            weath.main.temp.roundToInt(),
            weath.main.feels_like.roundToInt()
        )
    }
}