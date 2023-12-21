package ru.aston.news.di.api.headLine

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.aston.news.dto.Response

interface ApiBusinessService {
    /*@GET("top-headlines?country=us&apiKey=1f070c3add6f4e97a0babd69e1977be9")
     fun getAll(): Single<Response>*/

   //Business
    @GET("everything?q=business&apiKey=1f070c3add6f4e97a0babd69e1977be9")
   // fun getAll(@Query("sources") sources:String,): Single<Response>
    fun getAllBusiness(): Single<Response>


//General
    @GET("everything?q=general&apiKey=1f070c3add6f4e97a0babd69e1977be9")
    // fun getAll(@Query("sources") sources:String,): Single<Response>
    fun getAllGeneral(
    @Query("language") language:String?,
    @Query("sortBy") sortBy:String?,
): Single<Response>



    //CheckSource
    @GET("top-headlines?apiKey=1f070c3add6f4e97a0babd69e1977be9")
    suspend fun getAll(@Query("sources") sources:String,): retrofit2.Response<ru.aston.news.dto.Response>
}