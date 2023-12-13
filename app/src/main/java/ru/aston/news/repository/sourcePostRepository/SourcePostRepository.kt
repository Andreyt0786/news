package ru.aston.news.repository.sourcePostRepository

import kotlinx.coroutines.flow.Flow
import ru.aston.news.dto.SourcePost

interface SourcePostRepository {

    val dataSourcePost: Flow<List<SourcePost>>

    suspend fun getSourcePost()
}