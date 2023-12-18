package ru.aston.news.repository

import io.reactivex.rxjava3.core.Single
import ru.aston.news.dao.PostDao
import ru.aston.news.di.api.headLine.ApiBusinessService
import ru.aston.news.dto.Post
import ru.aston.news.dto.Response
import ru.aston.news.entity.headline.toDto
import ru.aston.news.entity.toDto
import ru.aston.news.entity.toEntity
import javax.inject.Inject


class PostRepositoryImpl @Inject constructor(
    private val apiService: ApiBusinessService,
    private val dao: PostDao
) : PostRepository {

    val country ="us"
    val sources = "cnn,bloomberg"
    private val categoryapi = "business"

    override fun getPosts(): Single<List<Post>> =
        dao.getAll()
            .zipWith(
                apiService.getAll().onErrorResumeNext { Single.just(Response()) }
            ) { dbData, response ->
                if (response.posts.isNullOrEmpty()) dbData.toDto()
                else {
                    dao.insertAll(response.posts.toEntity())
                    response.posts
                }
            }

    override val singlePost: List<Post> = dao.getPost().map{it.toDto()}

}