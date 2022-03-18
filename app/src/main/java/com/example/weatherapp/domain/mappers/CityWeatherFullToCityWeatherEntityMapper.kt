package com.example.weatherapp.domain.mappers

import com.example.weatherapp.data.api.entities.CityWeatherFull
import com.example.weatherapp.data.db.entities.CityWeatherEntity
import java.util.*

class CityWeatherFullToCityWeatherEntityMapper : (CityWeatherFull) -> CityWeatherEntity {

    override fun invoke(cityWeatherFull: CityWeatherFull): CityWeatherEntity {
        return with(cityWeatherFull) {
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
