package ru.aston.news.repository.sourcePostRepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import ru.aston.news.ApiError
import ru.aston.news.dao.sourcePostDao.SourcePostDao
import ru.aston.news.di.api.source.ApiSourceService
import ru.aston.news.dto.SourcePost
import ru.aston.news.entity.SourcePostEntity
import ru.aston.news.entity.toSourcePostEntity
import javax.inject.Inject

class SourcePostRepositoryImpl @Inject constructor(
    private val apiSourceService: ApiSourceService,
    private val sourcePostDao: SourcePostDao
) : SourcePostRepository {
    override val dataSourcePost: Flow<List<SourcePost>> =
        sourcePostDao.getAll().map { it.map(SourcePostEntity::toDto) }.flowOn(Dispatchers.Default)

    override suspend fun getSourcePost() {
        sourcePostDao.clear()
        val sourcePost = apiSourceService.getAll()
        if (!sourcePost.isSuccessful) {
            throw ApiError(sourcePost.code(), sourcePost.message())
        }
        val body =
            sourcePost.body()?.posts?: throw ApiError(sourcePost.code(), sourcePost.message())
        sourcePostDao.insertAll(body.toSourcePostEntity())
    }
}