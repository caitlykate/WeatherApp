package com.example.weatherapp.data

import androidx.room.Room
import com.example.weatherapp.App
import com.example.weatherapp.data.api.Api
import com.example.weatherapp.data.db.MainDataBase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://api.openweathermap.org/"

object DataComponent {

    private const val CONNECTION_TIMEOUT_MS = 2_000L

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT_MS, TimeUnit.MILLISECONDS)
//            .writeTimeout(CONNECTION_TIMEOUT_MS, TimeUnit.MILLISECONDS)
//            .readTimeout(CONNECTION_TIMEOUT_MS, TimeUnit.MILLISECONDS)
            .build()
    }

    val api: Api by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
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
