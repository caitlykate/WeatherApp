package com.example.weatherapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weatherapp.data.db.entities.CityWeatherEntity

@Database(
    entities = [
        CityWeatherEntity::class,
    ],
    version = 1,
)
abstract class MainDataBase : RoomDatabase() {

    abstract fun getDao(): Dao

    companion object {
        @Volatile
        private var INSTANCE: MainDataBase? =
            null      //эту переменную будем возвращать когда запрашиваем БД

        fun getDataBase(context: Context): MainDataBase {
            return INSTANCE
                ?: synchronized(this) {     //если несколько потоков пытаются запустить, то выполняем только в первом, в скобках чем блокируем (этим классом)
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        MainDataBase::class.java,
                        "weather.db"
                    ).build()
                    instance
                }
        }
    }
}