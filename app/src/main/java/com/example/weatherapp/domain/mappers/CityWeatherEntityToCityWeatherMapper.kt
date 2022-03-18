package com.example.weatherapp.domain.mappers

import com.example.weatherapp.data.db.entities.CityWeatherEntity
import com.example.weatherapp.domain.entities.CityWeather

class CityWeatherEntityToCityWeatherMapper : (CityWeatherEntity) -> CityWeather {

    override fun invoke(cityWeatherEntity: CityWeatherEntity): CityWeather {
        return CityWeather(
            id = cityWeatherEntity.id,
            cityName = cityWeatherEntity.cityName,
            temp = cityWeatherEntity.temp,
            feelsLike = cityWeatherEntity.feelsLike,
        )
    }
}
