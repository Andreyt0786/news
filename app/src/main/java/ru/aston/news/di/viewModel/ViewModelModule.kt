package ru.aston.news.di.viewModel

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import ru.aston.news.viewModel.CheckSourceViewModel
import ru.aston.news.viewModel.SourcePostViewModel


@Module
interface ViewModelModule {

    //binds связывает интерфейс и реализацию
    @Binds
    fun bindsSourcePostViewModel(viewModel: SourcePostViewModel): ViewModel

    @Binds
    fun bindsCheckSourceViewModel(viewModel: CheckSourceViewModel): ViewModel

}