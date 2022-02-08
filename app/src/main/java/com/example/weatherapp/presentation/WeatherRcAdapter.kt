package com.example.weatherapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.RcViewItemBinding
import com.example.weatherapp.domain.CityWeather

class WeatherRcAdapter() : RecyclerView.Adapter<WeatherRcAdapter.WeatherHolder>() {

    private val cityWeatherArray = ArrayList<CityWeather>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder {
        val binding = RcViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        holder.setData(cityWeatherArray[position])
    }

    override fun getItemCount(): Int {
        return cityWeatherArray.size
    }

    fun updateAdapter(newList: List<CityWeather>) {
//        val tempArray = ArrayList<CityWeather>()
//        tempArray.addAll(cityWeatherArray)
//        tempArray.addAll(newList)
//        val diffResult = DiffUtil.calculateDiff(DiffUtilHelper(cityWeatherArray, tempArray))
//        diffResult.dispatchUpdatesTo(this)
        cityWeatherArray.clear()
        cityWeatherArray.addAll(newList)
        notifyDataSetChanged()
    }

    class WeatherHolder(
        private val binding: RcViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setData(cityWeather: CityWeather) = with(binding) {
            cityNameTextView.text = cityWeather.cityName
            tempTextView.text = cityWeather.temp.toString()
            feelsLikeTextView.text = cityWeather.feelsLike.toString()
        }
    }
}

//class DiffUtilHelper(
//    private val oldList: List<CityWeather>,
//    private val newList: List<CityWeather>
//) : DiffUtil.Callback() {
//
//    override fun getOldListSize(): Int {
//        return oldList.size
//    }
//
//    override fun getNewListSize(): Int {
//        return newList.size
//    }
//
//    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//        //нужно ли перерисовывать
//        return oldList[oldItemPosition] == newList[newItemPosition]
//    }
//
//    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//        return oldList == newList
//    }
//}