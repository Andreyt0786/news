package ru.aston.news.repository

import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import ru.aston.news.dto.Post

interface PostRepository {


    //Business
    val singleBusinessPost: List<Post>
    fun getBusinessPosts(): Single<List<Post>>

    //General

    fun getGeneralPosts(): Single<List<Post>>
    val singleGeneralPost: List<Post>



    //CheckSource
    val dataCheckPost: Flow<List<Post>>

    val dataPost: List<Post>
    suspend fun getSourcePost(source: String)

    suspend fun add(post: Post)
}