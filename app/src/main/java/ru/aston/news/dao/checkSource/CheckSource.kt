package ru.aston.news.dao.checkSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.aston.news.entity.source.CheckSourceEntity

@Dao
interface CheckSourceDao {
    @Query("SELECT * FROM CheckSourceEntity")
    fun getAll(): Flow<List<CheckSourceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(posts: List<CheckSourceEntity>)

    @Query("DELETE FROM CheckSourceEntity")
    suspend fun clear()
}