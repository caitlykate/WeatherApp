package com.example.weatherapp.presentation.weatherdetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.domain.DomainComponent
import com.example.weatherapp.domain.entities.CityWeather
import com.example.weatherapp.domain.entities.ForecastWeather
import com.example.weatherapp.domain.interactors.GetCityWeatherForecastInteractor
import com.example.weatherapp.presentation.weatherlist.WeatherViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class WeatherDetailsViewModel(
    private val getCityWeatherForecastInteractor: GetCityWeatherForecastInteractor
) : ViewModel() {

    val weatherLiveData: LiveData<List<ForecastWeather>> get() = weatherMutableLiveData
    val hasErrorLiveData: LiveData<Boolean> get() = hasErrorMutableLiveData
    val isLoadingLiveData: LiveData<Boolean> get() = isLoadingMutableLiveData

    private val weatherMutableLiveData = MutableLiveData<List<ForecastWeather>>()
    private val hasErrorMutableLiveData = MutableLiveData<Boolean>()
    private val isLoadingMutableLiveData = MutableLiveData<Boolean>()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getWeather(cityId: Int) {
        compositeDisposable.add(
            weatherRequest(cityId)
        )
    }

    private fun weatherRequest(cityId: Int): Disposable {
        return getCityWeatherForecastInteractor.getWeather(cityId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                hasErrorMutableLiveData.value = false
                isLoadingMutableLiveData.value = true
            }
            .doOnTerminate {
                isLoadingMutableLiveData.value = false
            }
            .subscribe({ weather ->
                weatherMutableLiveData.value = weather
                hasErrorMutableLiveData.value = false
            },
                { error ->
                    hasErrorMutableLiveData.value = true
                    Log.e("FAIL", error.message.toString())
                }
            )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}

// TODO Вынести
class WeatherDetailsViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val weatherDetailsViewModel = WeatherDetailsViewModel(
            getCityWeatherForecastInteractor = DomainComponent.getCityWeatherForecastInteractor,
        )

        @Suppress("UNCHECKED_CAST")
        return weatherDetailsViewModel as? T ?: error("Unknown ViewModelClass")
    }
}

