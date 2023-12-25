package ru.aston.news.repository

import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import ru.aston.news.dto.Filters
import ru.aston.news.dto.Post

interface PostRepository {


    fun getFilters(): Filters


    //Business
    val singleBusinessPost: List<Post>
    fun getBusinessPosts(): Single<List<Post>>

    //General

    fun getGeneralPosts(
        language: String?,
        sortBy: String?,
        from: String?,
        to: String?
    ): Single<List<Post>>

    val singleGeneralPost: List<Post>

    fun getPostById(postId: Int): Post?

    suspend fun search(text: String)

    val searchPosts:Flow<List<Post>>
    //CheckSource
    val dataCheckPost: Flow<List<Post>>

    val dataPost: List<Post>
    suspend fun getSourcePost(source: String)

    suspend fun add(post: Post)

    fun saveRelevant(relevant: String?)

    fun saveLanguage(language: String?)

    fun saveData(startData: String?, toData: String?)


    //Saved

    val dataSavedPost: Flow<List<Post>>

    val singleSavedPost: List<Post>
    suspend fun remove(post: Post)

}