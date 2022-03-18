package com.example.weatherapp.domain

import android.util.Log
import com.example.weatherapp.data.db.MainDataBase
import com.example.weatherapp.data.db.entities.CityWeatherEntity
import com.example.weatherapp.domain.entities.CityWeather
import io.reactivex.rxjava3.core.Observable

interface GetAllCitiesWeatherInteractor {

    fun getWeather(): Observable<List<CityWeather>>
}

//typealias CityWeather = CityWeatherResponse

class GetAllCitiesWeatherInteractorImpl(
    private val db: MainDataBase,
    private val cityWeatherEntityToCityWeatherMapper: (CityWeatherEntity) -> CityWeather,
) : GetAllCitiesWeatherInteractor {

    override fun getWeather(): Observable<List<CityWeather>> {
        return db.getDao().getAllCitiesWeather()
            .map { cityWeatherEntities ->
                Log.e("LOAD", "LOAD")
                cityWeatherEntities.map { weather ->
                    cityWeatherEntityToCityWeatherMapper(weather)
                }
            }
    }
}
