package com.example.weatherapp.domain

import com.example.weatherapp.data.DataComponent
import com.example.weatherapp.domain.mappers.CityWeatherEntityToCityWeatherMapper
import com.example.weatherapp.domain.mappers.CityWeatherFullToCityWeatherEntityMapper

object DomainComponent {

    val getAllCitiesWeatherInteractor: GetAllCitiesWeatherInteractor
        get() = GetAllCitiesWeatherInteractorImpl(
            db = DataComponent.db,
            cityWeatherEntityToCityWeatherMapper = CityWeatherEntityToCityWeatherMapper(),
        )

    val updateWeatherInteractor: UpdateWeatherInteractor
        get() = UpdateWeatherInteractorImpl(
            api = DataComponent.api,
            db = DataComponent.db,
            weatherDomainMapper = CityWeatherFullToCityWeatherEntityMapper(),
        )
}
