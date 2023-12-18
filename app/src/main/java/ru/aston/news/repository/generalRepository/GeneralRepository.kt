package ru.aston.news.repository.generalRepository

import io.reactivex.rxjava3.core.Single
import ru.aston.news.dto.Post

interface GeneralRepository {

    fun getPosts(): Single<List<Post>>

    val singlePost:List<Post>
}