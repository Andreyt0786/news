
package ru.aston.news.di.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.aston.news.dao.PostDao
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
}

