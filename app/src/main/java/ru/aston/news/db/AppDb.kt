package ru.aston.news.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.aston.news.dao.PostDao
import ru.aston.news.entity.PostEntity

@Database(
    entities = [PostEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDb : RoomDatabase() {
    abstract fun postDao(): PostDao

}