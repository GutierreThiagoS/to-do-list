package com.example.todolistdigital

import android.app.Application
import androidx.room.Room
import com.example.todolistdigital.database.AppDataBase
import com.example.todolistdigital.di.getKoinModuleList
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    companion object{
        lateinit var context: App
        lateinit var db: AppDataBase
    }

    override fun onCreate() {
        super.onCreate()

        context = this

        startKoin {
            // declare used Android context
            androidContext(this@App)
            // declare modules
            modules (getKoinModuleList())
        }


        db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "database-to-do-list"
        ).build()
    }

}