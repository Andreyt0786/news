package ru.aston.news.di.navigation

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import com.github.terrakok.cicerone.Cicerone.Companion.create

@Module
class NavigationModule {

    @Provides
    @Singleton
    fun getCicerone(): Cicerone<Router> = Cicerone.create()

    @Provides
    @Singleton
    fun getRouter(cicerone: Cicerone<Router>) = cicerone.router

    @Provides
    @Singleton
    fun getNavigationHolder(cicerone: Cicerone<Router>) = cicerone.getNavigatorHolder()
}


