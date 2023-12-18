package ru.aston.news.repository.generalRepository

import io.reactivex.rxjava3.core.Single
import ru.aston.news.dao.general.GeneralDao
import ru.aston.news.di.api.headLine.ApiGeneralService
import ru.aston.news.dto.Post
import ru.aston.news.dto.Response
import ru.aston.news.entity.headline.toDto

import ru.aston.news.entity.headline.toGeneralEntity
import ru.aston.news.entity.toDto
import javax.inject.Inject

class GeneralRepositoryImpl @Inject constructor(
    private val apiService: ApiGeneralService,
    private val dao:GeneralDao
):GeneralRepository {


    override fun getPosts(): Single<List<Post>> =
        dao.getAll()
            .zipWith(
                apiService.getAll().onErrorResumeNext { Single.just(Response()) }
            ) { dbData, response ->
                if (response.posts.isNullOrEmpty()) dbData.toDto()// проверить потом
                else {
                    dao.insertAll(response.posts.toGeneralEntity())
                    response.posts
                }
            }

    override val singlePost: List<Post> = dao.getPost().map{it.toDto()}

}