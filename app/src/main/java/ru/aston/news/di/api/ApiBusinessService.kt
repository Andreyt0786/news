package ru.aston.news.di.api

import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import ru.aston.news.dto.Post

interface ApiBusinessService {
    @GET("top-headlines?country=us&apiKey=1f070c3add6f4e97a0babd69e1977be9")
     fun getAll(): Single<List<Post>>
}