package com.example.weatherapp.presentation.weatherdetails

import androidx.lifecycle.ViewModel
import com.example.weatherapp.domain.interactors.GetCityWeatherForecastInteractor

class WeatherDetailsViewModel(
    private val getCityWeatherForecastInteractor: GetCityWeatherForecastInteractor
) : ViewModel() {
}