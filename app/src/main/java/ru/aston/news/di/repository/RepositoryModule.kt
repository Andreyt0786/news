package ru.aston.news.di.repository

import dagger.Binds
import dagger.Module
import ru.aston.news.repository.PostRepository
import ru.aston.news.repository.PostRepositoryImpl
import ru.aston.news.repository.checkSourceRepository.CheckSourceRepository
import ru.aston.news.repository.checkSourceRepository.CheckSourceRepositoryImpl
import ru.aston.news.repository.generalRepository.GeneralRepository
import ru.aston.news.repository.generalRepository.GeneralRepositoryImpl
import ru.aston.news.repository.sourcePostRepository.SourcePostRepository
import ru.aston.news.repository.sourcePostRepository.SourcePostRepositoryImpl


@Module
interface RepositoryModule {

    //binds связывает интерфейс и реализацию
    @Binds
    fun bindsPostRepository(impl: PostRepositoryImpl): PostRepository

    @Binds
    fun bindsGeneralRepository(impl: GeneralRepositoryImpl):GeneralRepository

    @Binds
    fun bindsSourcePostRepository(impl: SourcePostRepositoryImpl): SourcePostRepository

    @Binds
    fun bindsCheckSourceRepository(impl: CheckSourceRepositoryImpl): CheckSourceRepository

}