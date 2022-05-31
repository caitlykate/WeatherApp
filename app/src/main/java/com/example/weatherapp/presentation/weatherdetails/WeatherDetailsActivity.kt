package com.example.weatherapp.presentation.weatherdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityWeatherDetailsBinding
import com.example.weatherapp.domain.entities.ForecastWeather
import com.example.weatherapp.other.DividerDecoration
import com.example.weatherapp.presentation.BaseActivity
import java.lang.StringBuilder
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class WeatherDetailsActivity : BaseActivity() {

    private val binding: ActivityWeatherDetailsBinding by lazy(mode = LazyThreadSafetyMode.NONE) {
        ActivityWeatherDetailsBinding.inflate(layoutInflater)
    }
    private val cityId: Int by lazy(mode = LazyThreadSafetyMode.NONE) {
        intent.getIntExtra(CITY_WEATHER_ID_KEY, DEFAULT_CITY_ID)
    }

    private val weatherDetailsViewModel: WeatherDetailsViewModel by viewModels { WeatherDetailsViewModelFactory() }
    private val adapter = WeatherDetailsRcAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        weatherDetailsViewModel.getWeather(cityId)
        initRecyclerView()
        observeToLiveData()
    }

    private fun initRecyclerView() = with(binding.forecastRecyclerView) {
        this@with.adapter = this@WeatherDetailsActivity.adapter
        val dividerDecoration =
            DividerDecoration(this.context, R.drawable.item_divider_background).apply {
                isBottom = true
                isTop = true
            }
        addItemDecoration(dividerDecoration)
    }

    private fun observeToLiveData() {
        weatherDetailsViewModel.weatherLiveData.observe(this) { weatherList ->
            adapter.forecastWeatherList = weatherList
            updateUpperCityWeather(weatherList.first())
        }
        weatherDetailsViewModel.hasErrorLiveData.observe(this) { hasError ->
            binding.errorTextView.isVisible = hasError
        }
        weatherDetailsViewModel.isLoadingLiveData.observe(this) { isLoading ->
            binding.progressBar.isVisible = isLoading
            binding.upperProgressBar.isVisible = isLoading
            binding.upperWeatherLayout.isVisible = !isLoading
        }
    }

    private fun updateUpperCityWeather(weather: ForecastWeather) = with(binding) {

        cityNameTextView.text = intent.getStringExtra(CITY_WEATHER_NAME_KEY)

        val date = Date(Timestamp(weather.date).getTime()*1000)
        timeTextView.text = SimpleDateFormat("dd MMMM ", Locale("ru")).format(date) + SimpleDateFormat("HH:MM", Locale("ru")).format(date)

        tempTextView.text = weather.temp.toString()
        feelsLikeTextView.text = getString(R.string.feels_like, weather.feelsLike)

        val icon_res = StringBuilder("https://openweathermap.org/img/wn/")
            .append(weather.icon)
            .append("@4x.png")
        Glide.with(applicationContext)
            .load(icon_res.toString())
            .into(upperImageView)

        windSpeed.text = getString(R.string.wind_speed, weather.windSpeed)
        humidity.text = getString(R.string.humidity, weather.humidity)
        pressure.text = getString(R.string.pressure, weather.pressure)
    }

    companion object {
        fun getIntent(
            context: Context,
            cityId: Int,
            cityName: String
        ): Intent {
            return Intent(context, WeatherDetailsActivity::class.java)
                .putExtra(CITY_WEATHER_ID_KEY, cityId)
                .putExtra(CITY_WEATHER_NAME_KEY, cityName)
        }

        private const val CITY_WEATHER_ID_KEY = "CITY_WEATHER_ID_KEY"
        private const val DEFAULT_CITY_ID = 0
        private const val CITY_WEATHER_NAME_KEY = "CITY_WEATHER_NAME_KEY"
    }
}