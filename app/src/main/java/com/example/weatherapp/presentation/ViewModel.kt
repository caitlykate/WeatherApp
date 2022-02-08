package com.example.weatherapp.presentation

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.domain.DomainComponent.getWeatherInteractor
import com.example.weatherapp.domain.GetWeatherInteractor
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

const val TAG = "test"


class WeatherViewModel
    (
    private val getWeatherInteractor: GetWeatherInteractor
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    init {
        getWeather()
    }

    fun getWeather() {

        val disposable = getWeatherInteractor.getWeather()
            .subscribeOn(Schedulers.newThread())                  //подписываемся на одном потоке через планировщика
            .observeOn(AndroidSchedulers.mainThread())             //наблюдаем на другом
            .subscribe({
                Log.d(TAG, "new data $it")
            }, {
                Log.d(TAG, "error: ${it.message}")
            }, {
                Log.d(TAG, "complete")
            })

        compositeDisposable.add(
            disposable
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    //получать доступ к ViewModel мы будем через делегата (спец. метод viewModels)
    //и в него нужно передать (если конструктор VM не пустой) в качестве аргумента фабрику VM:
    //так рекомендуют делать на оф сайте
    class WeatherViewModelFactory(private val getWeatherInteractor: GetWeatherInteractor) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            //если может быть передан
            if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {        //или if (modelClass == MainViewModel::class.java)
                @Suppress("UNCHECKED_CAST")
                return WeatherViewModel(getWeatherInteractor) as T                 //нужно привести тип к дженерику Т
            }
            throw IllegalArgumentException("Unknown ViewModelClass")
        }
        //в качестве аргумента приходит класс VM и в результат мы должны передать уже саму созданную VM

    }
}


fun Activity.factory() = WeatherViewModel.WeatherViewModelFactory(getWeatherInteractor)

