
package ru.aston.news.di.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.aston.news.dao.PostDao
import ru.aston.news.dao.checkSource.CheckSourceDao
import ru.aston.news.dao.general.GeneralDao
import ru.aston.news.dao.savedSource.SavedDao
import ru.aston.news.dao.search.SearchDao
import ru.aston.news.dao.sourcePostDao.SourcePostDao
import ru.aston.news.db.AppDb
import javax.inject.Singleton


@Module
class DbModule() {

    @Singleton
    //для создания функции в ручную пишется аннотация
    @Provides
    fun provideDb(
        context: Context
    ): AppDb = Room.databaseBuilder(context, AppDb::class.java, "app.db")
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()

    @Provides
    fun providePostDao(
        appDb: AppDb
    ): PostDao = appDb.postDao()


    @Provides
    fun provideGeneralDao(
        appDb: AppDb
    ): GeneralDao = appDb.generalDao()

    @Provides
    fun provideSourcePostDao(
        appDb: AppDb
    ): SourcePostDao = appDb.sourcePostDao()

    @Provides
    fun provideCheckSourceDao(
        appDb: AppDb
    ): CheckSourceDao = appDb.checkSourceDao()


    @Provides
    fun provideSavedDao(
        appDb: AppDb
    ): SavedDao = appDb.savedDao()

    @Provides
    fun provideSearchDao(
        appDb: AppDb
    ): SearchDao = appDb.searchDao()
}

