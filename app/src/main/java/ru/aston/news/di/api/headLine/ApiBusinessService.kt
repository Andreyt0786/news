package ru.aston.news.di.api.headLine

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.aston.news.dto.Response

interface ApiBusinessService {

    //Business
    @GET("everything?q=business&pageSize=20&apiKey=1f070c3add6f4e97a0babd69e1977be9")

    fun getAllBusiness(
        @Query("language") language: String?,
        @Query("sortBy") sortBy: String?,
        @Query("from") from: String?,
        @Query("to") to: String?,
    ): Single<Response>

    //Travel
    @GET("everything?q=traveling&pageSize=20&apiKey=1f070c3add6f4e97a0babd69e1977be9")

    fun getAllTravel(
        @Query("language") language: String?,
        @Query("sortBy") sortBy: String?,
        @Query("from") from: String?,
        @Query("to") to: String?,
    ): Single<Response>


    //General
    @GET("everything?q=general&pageSize=20&apiKey=1f070c3add6f4e97a0babd69e1977be9")

    fun getAllGeneral(
        @Query("language") language: String?,
        @Query("sortBy") sortBy: String?,
        @Query("from") from: String?,
        @Query("to") to: String?,
    ): Single<Response>


    //CheckSource
    @GET("top-headlines?apiKey=1f070c3add6f4e97a0babd69e1977be9")
    suspend fun getAll(@Query("sources") sources: String): retrofit2.Response<ru.aston.news.dto.Response>

    @GET("everything?apiKey=1f070c3add6f4e97a0babd69e1977be9")
    suspend fun getSearchAll(@Query("q") q: String): retrofit2.Response<ru.aston.news.dto.Response>

}