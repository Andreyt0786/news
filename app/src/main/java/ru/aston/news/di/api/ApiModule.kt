package ru.aston.news.di.api

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.create
import ru.aston.news.BuildConfig
import ru.aston.news.di.api.headLine.ApiBusinessService
import ru.aston.news.di.api.source.ApiSourceService
import javax.inject.Singleton

@Module
class ApiModule {
    companion object {
        private const val BASE_URL = "https://newsapi.org/v2/"
    }

    @Singleton
    @Provides
    fun provideLogging(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        if (BuildConfig.DEBUG) {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideOkHttp(
        logging: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        /* .addInterceptor { chain ->
             val token = "1f070c3add6f4e97a0babd69e1977be9"
             val request = if (token != null) {
                 chain.request()
                     .newBuilder()
                     .addHeader("Authorization", token)
                     .build()
             } else {
                 chain.request()
             }

             chain.proceed(request)
         }*/
        .build()


    @Singleton
    @Provides
    fun provideRetrofit(
        okhttp: OkHttpClient,
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .client(okhttp)
        .build()

    @Singleton
    @Provides
    fun provideApiBusinessService(
        retrofit: Retrofit
    ): ApiBusinessService = retrofit.create()


    @Singleton
    @Provides
    fun provideApiSourceService(
        retrofit: Retrofit
    ): ApiSourceService = retrofit.create()


}

