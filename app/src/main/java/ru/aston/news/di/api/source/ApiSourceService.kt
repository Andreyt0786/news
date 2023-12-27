package ru.aston.news.di.api.source

import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Query
import ru.aston.news.dto.ResponseSource


interface ApiSourceService {

    @GET("top-headlines/sources?apiKey=1f070c3add6f4e97a0babd69e1977be9")

    suspend fun getAll(
        @Query("language") language: String?,
    ): Response<ResponseSource>
}