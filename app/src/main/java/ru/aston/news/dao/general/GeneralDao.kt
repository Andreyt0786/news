package ru.aston.news.dao.general

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Single
import ru.aston.news.entity.headline.GeneralEntity
import ru.aston.news.entity.PostEntity


    @Dao
    interface GeneralDao {
        @Query("SELECT * FROM GeneralEntity")
        fun getAll(): Single<List<PostEntity>>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insert(post: GeneralEntity)

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insertAll(posts: List<GeneralEntity>)

    }
