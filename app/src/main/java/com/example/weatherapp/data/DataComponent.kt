package com.example.weatherapp.data

import androidx.room.Room
import com.example.weatherapp.App
import com.example.weatherapp.data.api.Api
import com.example.weatherapp.data.db.MainDataBase
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.openweathermap.org/"

object DataComponent {

    val api: Api by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(Api::class.java)
    }

    val db: MainDataBase by lazy {
        Room.databaseBuilder(
            App.appContext.applicationContext,
            MainDataBase::class.java,
            "weather.db"
        ).build()
    }
}
