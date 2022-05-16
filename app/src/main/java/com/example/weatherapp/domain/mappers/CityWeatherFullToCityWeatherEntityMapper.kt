package com.example.weatherapp.domain.mappers

import com.example.weatherapp.data.api.entities.CityWeatherData
import com.example.weatherapp.data.db.entities.CityWeatherEntity
import java.util.*

class CityWeatherFullToCityWeatherEntityMapper : (CityWeatherData) -> CityWeatherEntity {

    override fun invoke(cityWeatherData: CityWeatherData): CityWeatherEntity {
        return with(cityWeatherData) {
            CityWeatherEntity(
                id = id,
                cityName = name,
                temp = main.temp.toInt(),
                feelsLike = main.feels_like.toInt(),
                updateTime = Date().time,
            )
        }
    }
}
