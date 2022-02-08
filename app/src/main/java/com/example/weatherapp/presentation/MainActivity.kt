package com.example.weatherapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.weatherapp.databinding.ActivityMainBinding
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.TAG
import com.example.weatherapp.domain.CityWeather

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val weatherViewModel: WeatherViewModel by viewModels { factory() }
    private val adapter = WeatherRcAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        observeToLiveData()
    }

    private fun initRecyclerView(){
        binding.rcView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }
    private fun observeToLiveData() {
        weatherViewModel.weatherLiveData.observe(this, ::onLoadingWeatherUpdate)
    }

    private fun onLoadingWeatherUpdate(weatherList: List<CityWeather>) {
        Log.d(TAG, weatherList.toString())
        adapter.updateAdapter(weatherList)
    }
}
