package com.example.weatherapp.domain

import com.example.weatherapp.data.dto.CityWeatherFull
import kotlin.math.roundToInt

class WeatherDomainMapperImpl : (CityWeatherFull) -> CityWeather {

    override fun invoke(weath: CityWeatherFull): CityWeather {
        return CityWeather(
            weath.name,
            weath.main.temp.roundToInt(),
            weath.main.feels_like.roundToInt()
        )
    }
}