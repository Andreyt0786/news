package ru.aston.news.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.aston.news.dao.PostDao
import ru.aston.news.dao.checkSource.CheckSourceDao
import ru.aston.news.dao.general.GeneralDao
import ru.aston.news.dao.savedSource.SavedDao
import ru.aston.news.dao.search.SearchDao
import ru.aston.news.dao.sourcePostDao.SourcePostDao
import ru.aston.news.entity.headline.GeneralEntity
import ru.aston.news.entity.PostEntity
import ru.aston.news.entity.SourcePostEntity
import ru.aston.news.entity.saved.SavedEntity
import ru.aston.news.entity.search.SearchEntity
import ru.aston.news.entity.source.CheckSourceEntity

@Database(
    entities = [
        PostEntity::class,
        GeneralEntity::class,
        SourcePostEntity::class,
        CheckSourceEntity::class,
        SavedEntity::class,
        SearchEntity::class],
    version = 3,
    exportSchema = false
)
abstract class AppDb : RoomDatabase() {
    abstract fun postDao(): PostDao

    abstract fun generalDao(): GeneralDao

    abstract fun sourcePostDao(): SourcePostDao

    abstract fun checkSourceDao(): CheckSourceDao

    abstract fun savedDao(): SavedDao

    abstract fun searchDao():SearchDao

}