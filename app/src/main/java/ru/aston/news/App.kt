package ru.aston.news

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import ru.aston.news.di.AppComponent
import ru.aston.news.di.DaggerAppComponent
import ru.aston.news.di.appModule.AppModule


class App : Application() {


    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        //dagger
       appComponent = DaggerAppComponent.builder()
           .appModule(AppModule(context = this))
           .build()
    }

    companion object {
        //dagger
        lateinit var appComponent:AppComponent
        internal lateinit var INSTANCE: App
            private set
    }
}