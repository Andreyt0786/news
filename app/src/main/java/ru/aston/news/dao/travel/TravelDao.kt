package ru.aston.news.dao.travel

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Single
import ru.aston.news.entity.travel.TravelEntity


@Dao
interface TravelDao {
    @Query("SELECT * FROM TravelEntity")
    fun getAll(): Single<List<TravelEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(post: TravelEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(posts: List<TravelEntity>)

    @Query("SELECT * FROM TravelEntity")
    fun getPost(): List<TravelEntity>

    @Query("DELETE FROM TravelEntity")
    fun clear()

}


