package ru.aston.news.di.viewModel

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import ru.aston.news.viewModel.CheckSourceViewModel
import ru.aston.news.viewModel.SavedViewModel
import ru.aston.news.viewModel.SearchViewModel
import ru.aston.news.viewModel.SingleBusinessViewModel
import ru.aston.news.viewModel.SingleGeneralViewModel
import ru.aston.news.viewModel.SourcePostViewModel


@Module
interface ViewModelModule {

    //binds связывает интерфейс и реализацию
    @Binds
    fun bindsSourcePostViewModel(viewModel: SourcePostViewModel): ViewModel

    @Binds
    fun bindsCheckSourceViewModel(viewModel: CheckSourceViewModel): ViewModel

    @Binds
    fun bindsSingleBusinessViewModel(viewModel: SingleBusinessViewModel): ViewModel


    @Binds
    fun bindsSingleGeneralViewModel(viewModel: SingleGeneralViewModel): ViewModel

    @Binds
    fun bindsSavedViewModel(viewModel: SavedViewModel): ViewModel

    @Binds
    fun bindsSearchViewModel(viewModel: SearchViewModel): ViewModel

}