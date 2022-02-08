package com.example.weatherapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.TAG
import com.example.weatherapp.domain.CityWeather
import com.example.weatherapp.domain.DomainComponent.getWeatherInteractor
import com.example.weatherapp.domain.GetWeatherInteractor
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class WeatherViewModel(
    private val getWeatherInteractor: GetWeatherInteractor,
) : ViewModel() {

    private val weatherMutableLiveData = MutableLiveData<List<CityWeather>>()
    val weatherLiveData: LiveData<List<CityWeather>> get() = weatherMutableLiveData

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    //lateinit var weatherObservable : Observable<CityWeather>

    init {
        getWeather()
    }

    private fun getWeather() {
        compositeDisposable.add(
            getWeatherInteractor.getWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { cityWeather ->
                        Log.d(TAG, "Data: $cityWeather")
                        weatherMutableLiveData.value = cityWeather
                    },
                    {
                        Log.d(TAG, "Error: ${it.message}")
                    }
                )
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}

class WeatherViewModelFactory(
    private val getWeatherInteractor: GetWeatherInteractor,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WeatherViewModel(getWeatherInteractor) as T
        }
        throw IllegalArgumentException("Unknown ViewModelClass")
    }
}

fun factory() = WeatherViewModelFactory(getWeatherInteractor)
