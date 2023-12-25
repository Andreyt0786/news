package ru.aston.news.di.repository

import dagger.Binds
import dagger.Module
import ru.aston.news.repository.PostRepository
import ru.aston.news.repository.PostRepositoryImpl
import ru.aston.news.repository.sourcePostRepository.SourcePostRepository
import ru.aston.news.repository.sourcePostRepository.SourcePostRepositoryImpl
import javax.inject.Singleton


@Module
interface RepositoryModule {

    //binds связывает интерфейс и реализацию
    @Binds
    @Singleton
    fun bindsPostRepository(impl: PostRepositoryImpl): PostRepository

    @Binds
    @Singleton
    fun bindsSourcePostRepository(impl: SourcePostRepositoryImpl): SourcePostRepository

}