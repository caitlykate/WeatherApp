package com.example.weatherapp.presentation.weatherdetails

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ItemForecastBinding
import com.example.weatherapp.domain.entities.ForecastWeather
import java.text.DateFormat
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class WeatherDetailsRcAdapter : RecyclerView.Adapter<WeatherDetailsRcAdapter.WeatherForecastHolder>() {

    var forecastWeatherList: List<ForecastWeather> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newCityWeatherList) {
            field = newCityWeatherList
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherForecastHolder {
        return WeatherForecastHolder(parent = parent)
    }

    override fun onBindViewHolder(holder: WeatherForecastHolder, position: Int) {
        holder.onBind(forecastWeather = forecastWeatherList[position])
    }

    override fun getItemCount(): Int {
        return forecastWeatherList.size
    }

    inner class WeatherForecastHolder(
        parent: ViewGroup,
        private val binding: ItemForecastBinding = ItemForecastBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var forecastWeather: ForecastWeather

        fun onBind(forecastWeather: ForecastWeather) = with(binding) {
            this@WeatherForecastHolder.forecastWeather = forecastWeather

//            val russian = Locale("ru")
//            val newMonths = arrayOf(
//                "января", "февраля", "марта", "апреля", "мая", "июня",
//                "июля", "августа", "сентября", "октября", "ноября", "декабря"
//            )
//            val dfs: DateFormatSymbols = DateFormatSymbols.getInstance(russian)
//            dfs.setMonths(newMonths)
//            val df: DateFormat = DateFormat.getDateInstance(DateFormat.LONG, russian)
//            val sdf = df as SimpleDateFormat
//            sdf.dateFormatSymbols = dfs
//
//            val jud = SimpleDateFormat("yyyy-MM-dd").parse("2014-02-28")
//            val month = sdf.format(jud)
//            println(month) // output: 28 февраля 2014 г.

            val date = Date(forecastWeather.date)
            dateTextView.text = SimpleDateFormat("DD MMMM", Locale("ru")).format(date)
            timeTextView.text = SimpleDateFormat("HH:MM", Locale("ru")).format(date)
            //weatherPicImageView = forecastWeather.icon
            maxTempTextView.text = forecastWeather.tempMax.toString()
            minTempTextView.text = forecastWeather.tempMin.toString()
        }
    }
}