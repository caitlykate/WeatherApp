package com.example.weatherapp.domain

import com.example.weatherapp.data.DataComponent

object DomainComponent {

    val getWeatherInteractor: GetWeatherInteractor
        get() = GetWeatherInteractorImpl(
            api = DataComponent.api,
        )
}