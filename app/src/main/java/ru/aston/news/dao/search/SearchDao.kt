package ru.aston.news.dao.search

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.aston.news.entity.saved.SavedEntity
import ru.aston.news.entity.search.SearchEntity


@Dao
    interface SearchDao {


        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insert(post: SearchEntity)

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insertAll(posts: List<SearchEntity>)


        @Query("SELECT * FROM SearchEntity")
        fun getSearchListAll(): Flow<List<SearchEntity>>


        @Query("SELECT * FROM SearchEntity")
        fun getSearchList(): List<SearchEntity>

        @Query("DELETE FROM SavedEntity")
        suspend fun clear()


}