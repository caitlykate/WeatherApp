package com.example.weatherapp.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "city_weather")
data class CityWeatherEntity(

    @PrimaryKey //(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "city_name")
    val cityName: String,

    @ColumnInfo(name = "temp")
    val temp: Int,

    @ColumnInfo(name = "feels_like")
    val feelsLike: Int,

    @ColumnInfo(name = "update_time")
    val updateTime: Long

) : Serializable
