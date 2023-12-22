package ru.aston.news.dao.savedSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import ru.aston.news.entity.headline.GeneralEntity
import ru.aston.news.entity.saved.SavedEntity
import ru.aston.news.entity.source.CheckSourceEntity

@Dao
interface SavedDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(post: SavedEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(posts: List<SavedEntity>)


    @Query("SELECT * FROM SavedEntity")
    fun getSavedAll(): Flow<List<SavedEntity>>


    @Query("DELETE FROM SavedEntity")
    suspend fun clear()


    @Query("SELECT * FROM SavedEntity")
    fun getSinglePost(): List<SavedEntity>


}