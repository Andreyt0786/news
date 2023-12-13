package ru.aston.news.di.api.headLine

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.aston.news.dto.Response

interface ApiGeneralService {

    @GET("top-headlines?category=general&country=us&apiKey=1f070c3add6f4e97a0babd69e1977be9")
   // fun getAll(@Query("sources") sources:String,): Single<Response>
    fun getAll(): Single<Response>
}