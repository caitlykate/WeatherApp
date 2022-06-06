package com.example.weatherapp.presentation.weatherlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.domain.*
import com.example.weatherapp.domain.entities.CityWeather
import com.example.weatherapp.domain.interactors.UpdateWeatherInteractor
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class WeatherViewModel(
    private val getAllCitiesWeatherInteractor: GetAllCitiesWeatherInteractor,
    private val updateWeatherInteractor: UpdateWeatherInteractor
) : ViewModel() {

    val weatherLiveData: LiveData<List<CityWeather>> get() = weatherMutableLiveData
    val hasErrorLiveData: LiveData<Boolean> get() = hasErrorMutableLiveData
    val isLoadingLiveData: LiveData<Boolean> get() = isLoadingMutableLiveData

    private val weatherMutableLiveData = MutableLiveData<List<CityWeather>>()
    private val hasErrorMutableLiveData = MutableLiveData<Boolean>()
    private val isLoadingMutableLiveData = MutableLiveData<Boolean>()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private var reloadWeatherDisposable: Disposable = updateWeather()


    init {
        compositeDisposable.add(
            getWeather()
        )
    }

    fun onRefresh() {
        reloadWeatherDisposable.dispose()
        reloadWeatherDisposable = updateWeather()
    }

    private fun getWeather(): Disposable {
        return getAllCitiesWeatherInteractor.getWeather()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { cityWeather ->
                weatherMutableLiveData.value = cityWeather
            }
    }

    private fun updateWeather(): Disposable {
        return updateWeatherInteractor.updateWeather()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                hasErrorMutableLiveData.value = false
                isLoadingMutableLiveData.value = true
            }
            .doOnTerminate {
                isLoadingMutableLiveData.value = false
            }
            .subscribe(
                {
                    hasErrorMutableLiveData.value = false
                },
                {
                    hasErrorMutableLiveData.value = true
                }
            )
    }

    override fun onCleared() {
        reloadWeatherDisposable.dispose()
        compositeDisposable.dispose()
        super.onCleared()
    }
}

// TODO Вынести
class WeatherViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val weatherViewModel = WeatherViewModel(
            getAllCitiesWeatherInteractor = DomainComponent.getAllCitiesWeatherInteractor,
            updateWeatherInteractor = DomainComponent.updateWeatherInteractor,
        )

        @Suppress("UNCHECKED_CAST")
        return weatherViewModel as? T ?: error("Unknown ViewModelClass")
    }
}

