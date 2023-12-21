package ru.aston.news.repository

import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import ru.aston.news.dto.Filters
import ru.aston.news.dto.Post

interface PostRepository {

   // val filterState:MutableLiveData<Filters>

    val filtersState:Filters
    //Business
    val singleBusinessPost: List<Post>
    fun getBusinessPosts(): Single<List<Post>>

    //General

    fun getGeneralPosts(language: String?, sortBy:String?): Single<List<Post>>
    val singleGeneralPost: List<Post>



    //CheckSource
    val dataCheckPost: Flow<List<Post>>

    val dataPost: List<Post>
    suspend fun getSourcePost(source: String)

    suspend fun add(post: Post)

   fun saveRelevant(relevant:String?)

    fun saveLanguage(language:String?)
}