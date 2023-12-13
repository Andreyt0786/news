package ru.aston.news.repository

import io.reactivex.rxjava3.core.Single
import ru.aston.news.dto.Post

interface PostRepository {

    fun getPosts(): Single<List<Post>>
}