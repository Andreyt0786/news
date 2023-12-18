package ru.aston.news.repository.checkSourceRepository

import kotlinx.coroutines.flow.Flow
import ru.aston.news.dto.Post

interface CheckSourceRepository {
    val dataCheckPost: Flow<List<Post>>

    val dataPost :List<Post>
    suspend fun getSourcePost(source:String)

    suspend fun add(post:Post)
}