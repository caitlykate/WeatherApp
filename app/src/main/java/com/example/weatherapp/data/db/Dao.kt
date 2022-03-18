package com.example.weatherapp.data.db

import androidx.room.*
import androidx.room.Dao
import com.example.weatherapp.data.db.entities.CityWeatherEntity
import io.reactivex.rxjava3.core.Observable

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCityWeather(cityWeathers: List<CityWeatherEntity>)

    @Query (value = "SELECT * FROM city_weather")
    fun getAllCitiesWeather(): Observable<List<CityWeatherEntity>>

    @Update
    suspend fun updateCityWeather(cityWeather: CityWeatherEntity)
}