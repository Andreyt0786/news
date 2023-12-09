package ru.aston.news.di.repository

import dagger.Binds
import dagger.Module
import ru.aston.news.repository.PostRepository
import ru.aston.news.repository.PostRepositoryImp



@Module
interface RepositoryModule {

    //binds связывает интерфейс и реализацию
    @Binds
    fun bindsPostRepository(impl: PostRepositoryImp): PostRepository

}