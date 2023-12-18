package ru.aston.news.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Single
import ru.aston.news.entity.PostEntity
import ru.aston.news.entity.headline.GeneralEntity

@Dao
interface PostDao {
    @Query("SELECT * FROM PostEntity")
    fun getAll(): Single<List<PostEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(post: PostEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(posts: List<PostEntity>)

    @Query("SELECT * FROM PostEntity")
    fun getPost(): List<PostEntity>
}