package com.example.weatherapp.presentation.weatherdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.weatherapp.databinding.ActivityWeatherDetailsBinding
import com.example.weatherapp.presentation.BaseActivity
import com.example.weatherapp.presentation.weatherlist.WeatherViewModelFactory

class WeatherDetailsActivity : BaseActivity() {

    private val binding: ActivityWeatherDetailsBinding by lazy(mode = LazyThreadSafetyMode.NONE) {
        ActivityWeatherDetailsBinding.inflate(layoutInflater)
    }
    private val cityWeatherId: Int by lazy(mode = LazyThreadSafetyMode.NONE) {
        intent.getIntExtra(CITY_WEATHER_ID_KEY, DEFAULT_CITY_ID_KEY)
    }
    private val weatherDetailsViewModel: WeatherDetailsViewModel by viewModels { WeatherDetailsViewModelFactory() }
    private val adapter = WeatherDetailsRcAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        weatherDetailsViewModel.getWeather(cityWeatherId)
        initRecyclerView()
        observeToLiveData()
    }

    private fun initRecyclerView() {
        binding.forecastRecyclerView.adapter = adapter
    }

    private fun observeToLiveData() {
        weatherDetailsViewModel.weatherLiveData.observe(this) { weatherList ->
            adapter.forecastWeatherList = weatherList
        }
        weatherDetailsViewModel.hasErrorLiveData.observe(this) { hasError ->
            binding.errorTextView.isVisible = hasError
        }
        weatherDetailsViewModel.isLoadingLiveData.observe(this) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }
    }

    companion object {
        fun getIntent(
            context: Context,
            cityWeatherId: Int,
        ): Intent {
            return Intent(context, WeatherDetailsActivity::class.java)
                .putExtra(CITY_WEATHER_ID_KEY, cityWeatherId)
        }

        private const val CITY_WEATHER_ID_KEY = "CITY_WEATHER_ID_KEY"

        private const val DEFAULT_CITY_ID_KEY = 0
    }
}