package ru.aston.news.di.api.source

import retrofit2.http.GET
import retrofit2.Response
import ru.aston.news.dto.ResponseSource


interface ApiSourceService {

    @GET("top-headlines/sources?country=us&apiKey=1f070c3add6f4e97a0babd69e1977be9")
    // fun getAll(@Query("sources") sources:String,): Single<Response>
    suspend fun getAll(): Response<ResponseSource>
}