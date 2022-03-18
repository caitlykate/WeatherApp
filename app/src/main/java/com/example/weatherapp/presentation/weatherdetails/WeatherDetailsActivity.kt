package com.example.weatherapp.presentation.weatherdetails

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherapp.R

class WeatherDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_details)
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
    }
}