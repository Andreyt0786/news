package ru.aston.news.di.repository

import dagger.Binds
import dagger.Module
import ru.aston.news.repository.FiltersRepository
import ru.aston.news.repository.FiltersRepositoryImpl
import ru.aston.news.repository.PostRepository
import ru.aston.news.repository.PostRepositoryImpl
import ru.aston.news.repository.sourcePostRepository.SourcePostRepository
import ru.aston.news.repository.sourcePostRepository.SourcePostRepositoryImpl
import javax.inject.Singleton


@Module
interface RepositoryModule {

    //binds связывает интерфейс и реализацию
    @Binds
    fun bindsPostRepository(impl: PostRepositoryImpl): PostRepository

    @Binds
    fun bindsSourcePostRepository(impl: SourcePostRepositoryImpl): SourcePostRepository


    @Binds
    @Singleton
    fun bindsFiltersRepository(impl: FiltersRepositoryImpl): FiltersRepository
}