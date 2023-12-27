package ru.aston.news.dao.search

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.aston.news.entity.saved.SavedEntity
import ru.aston.news.entity.search.SearchEntity
import ru.aston.news.entity.travel.TravelEntity


@Dao
interface SearchDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(post: SearchEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(posts: List<SearchEntity>)


    @Query("SELECT * FROM SearchEntity ORDER BY idPost DESC")
    fun getSearchListAll(): Flow<List<SearchEntity>>


    @Query("SELECT * FROM SearchEntity ORDER BY idPost DESC")
    fun getSearchList(): List<SearchEntity>

    @Query("SELECT * FROM SearchEntity")
    fun getPost(): List<SearchEntity>

    @Query("DELETE FROM SearchEntity")
    suspend fun clear()


}