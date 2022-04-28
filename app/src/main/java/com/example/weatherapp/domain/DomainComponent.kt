package com.example.weatherapp.domain

import com.example.weatherapp.data.DataComponent
import com.example.weatherapp.domain.interactors.GetCityWeatherForecastInteractor
import com.example.weatherapp.domain.interactors.GetCityWeatherForecastInteractorImpl
import com.example.weatherapp.domain.mappers.CityWeatherEntityToCityWeatherMapper
import com.example.weatherapp.domain.mappers.CityWeatherFullToCityWeatherEntityMapper
import com.example.weatherapp.domain.mappers.ForecastWeatherMapper

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

    val getCityWeatherForecastInteractor: GetCityWeatherForecastInteractor
        get() = GetCityWeatherForecastInteractorImpl(
            api = DataComponent.api,
            forecastWeatherMapper = ForecastWeatherMapper(),
        )
}
