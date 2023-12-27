package ru.aston.news.repository

import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import ru.aston.news.dto.Filters
import ru.aston.news.dto.Post

interface PostRepository {
    val singleTravelPost: List<Post>
    fun getTravelPosts(
        language: String?,
        sortBy: String?,
        from: String?,
        to: String?
    ): Single<List<Post>>


    //Business
    val singleBusinessPost: List<Post>
    fun getBusinessPosts(
        language: String?,
        sortBy: String?,
        from: String?,
        to: String?
    ): Single<List<Post>>

    //General

    fun getGeneralPosts(
        language: String?,
        sortBy: String?,
        from: String?,
        to: String?
    ): Single<List<Post>>

    val singleGeneralPost: List<Post>
    fun addGeneral(post:Post)
    fun removeGeneral(post: Post)

    //fun getPostById(postId: Int): Post?

    suspend fun search(text: String)
    suspend fun searchBD(text: String)
    val singleSearchPost: List<Post>

    val searchPosts: Flow<List<Post>>

    suspend fun clearSearchDao()

    //CheckSource
    val dataCheckPost: Flow<List<Post>>

    val dataPost: List<Post>
    suspend fun getSourcePost(source: String)

    suspend fun add(post: Post)




    //Saved

    val dataSavedPost: Flow<List<Post>>

    val singleSavedPost: List<Post>
    suspend fun remove(post: Post)

    fun deleteOld(time:Long)

}