package ru.aston.news.dao.general

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Single
import ru.aston.news.entity.headline.GeneralEntity


@Dao
interface GeneralDao {
    @Query("SELECT * FROM GeneralEntity ORDER BY idPost DESC")
    fun getAll(): Single<List<GeneralEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(post: GeneralEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(posts: List<GeneralEntity>)

    @Query("SELECT * FROM GeneralEntity")
    fun getPost(): List<GeneralEntity>

    @Query("DELETE FROM GeneralEntity")
     fun clear()

}


