package ru.aston.news.di.api.source

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCheckSourceService {


        @GET("top-headlines?country=us&apiKey=1f070c3add6f4e97a0babd69e1977be9")
        suspend fun getAll(@Query("sources") sources:String,): retrofit2.Response<ru.aston.news.dto.Response>
    }
