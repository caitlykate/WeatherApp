package com.example.weatherapp.presentation.weatherlist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.RcViewItemBinding
import com.example.weatherapp.domain.entities.CityWeather

class WeatherRcAdapter(
    private val onItemClick: (cityWeather: CityWeather) -> Unit,
) : RecyclerView.Adapter<WeatherRcAdapter.WeatherHolder>() {

    var cityWeatherList: List<CityWeather> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newCityWeatherList) {
            field = newCityWeatherList
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder {
        return WeatherHolder(parent = parent)
    }

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        holder.onBind(cityWeather = cityWeatherList[position])
    }

    override fun getItemCount(): Int {
        return cityWeatherList.size
    }

    inner class WeatherHolder(
        parent: ViewGroup,
        private val binding: RcViewItemBinding = RcViewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var cityWeather: CityWeather

        init {
            itemView.setOnClickListener {
                this@WeatherRcAdapter.onItemClick(cityWeather)
            }
        }

        fun onBind(cityWeather: CityWeather) = with(binding) {
            this@WeatherHolder.cityWeather = cityWeather

            cityNameTextView.text = cityWeather.cityName
            tempTextView.text = cityWeather.temp.toString()
            feelsLikeTextView.text = cityWeather.feelsLike.toString()
        }
    }
}