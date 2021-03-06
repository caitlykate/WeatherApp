package com.example.weatherapp.presentation.weatherlist

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.presentation.BaseActivity
import com.example.weatherapp.presentation.weatherdetails.WeatherDetailsActivity

class AllCitiesWeatherActivity : BaseActivity() {

    private val binding: ActivityMainBinding by lazy(mode = LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val weatherViewModel: WeatherViewModel by viewModels { WeatherViewModelFactory() }
    private val adapter = WeatherRcAdapter(
        onItemClick = { cityWeather ->
            startActivity(
                WeatherDetailsActivity.getIntent(
                    context = this,
                    cityId = cityWeather.id,
                    cityName = cityWeather.cityName
                )
            )
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initRecyclerView()
        initSwipeRefreshLayout()
        observeToLiveData()
    }

    private fun initSwipeRefreshLayout() {
        binding.weatherSwipeRefreshLayout.setOnRefreshListener {
            binding.weatherSwipeRefreshLayout.isRefreshing = false
            weatherViewModel.onRefresh()
        }
    }

    private fun initRecyclerView() {
        binding.weatherRecyclerView.adapter = adapter
    }

    private fun observeToLiveData() {
        weatherViewModel.weatherLiveData.observe(this) { weatherList ->
            adapter.cityWeatherList = weatherList
        }
        weatherViewModel.hasErrorLiveData.observe(this) { hasError ->
            binding.errorTextView.isVisible = hasError
            binding.weatherRecyclerView.isVisible = !hasError
        }
        weatherViewModel.isLoadingLiveData.observe(this) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }
    }
}