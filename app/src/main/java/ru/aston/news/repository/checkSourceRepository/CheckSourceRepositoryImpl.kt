package ru.aston.news.repository.checkSourceRepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import ru.aston.news.ApiError
import ru.aston.news.dao.checkSource.CheckSourceDao
import ru.aston.news.di.api.source.ApiCheckSourceService
import ru.aston.news.dto.Post
import ru.aston.news.entity.source.CheckSourceEntity
import ru.aston.news.entity.source.toCheckSourceEntity
import javax.inject.Inject

class CheckSourceRepositoryImpl @Inject constructor(
    private val apiCheckSourceService: ApiCheckSourceService,
    private val checkSourceDao: CheckSourceDao,
) : CheckSourceRepository {
    override val dataCheckPost: Flow<List<Post>> =
        checkSourceDao.getAll().map { it.map(CheckSourceEntity::toDto) }.flowOn(Dispatchers.Default)

    override suspend fun getSourcePost(source: String) {
        checkSourceDao.clear()
        val sourcePost = apiCheckSourceService.getAll(source)
        if (!sourcePost.isSuccessful) {
            throw ApiError(sourcePost.code(), sourcePost.message())
        }
        val body =
            sourcePost.body()?.posts ?: throw ApiError(sourcePost.code(), sourcePost.message())
        checkSourceDao.insertAll(body.toCheckSourceEntity())
    }

    override val dataPost: List<Post> = checkSourceDao.getPost().map{it.toDto()}

    override suspend fun add(post:Post){
        //TODO savedDao.insert(SavedEntity.fromDto(post))
    }
}