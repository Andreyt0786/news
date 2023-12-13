package ru.aston.news.dao.sourcePostDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.aston.news.entity.SourcePostEntity

@Dao
interface SourcePostDao {


        @Query("SELECT * FROM SourcePostEntity ")
        fun getAll(): Flow<List<SourcePostEntity>>

       /* @Query("SELECT * FROM PostEntity ORDER BY id DESC")
        fun getPagingSource(): PagingSource<Int, PostEntity>*/

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(post: SourcePostEntity)

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertAll(posts: List<SourcePostEntity>)

        @Query("DELETE FROM SourcePostEntity")
        suspend fun clear()

}