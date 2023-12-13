package ru.aston.news.di.presenter

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import moxy.MvpPresenter
import moxy.MvpView
import ru.aston.news.presenters.general.GeneralPresenter
import ru.aston.news.presenters.general.GeneralView
import ru.aston.news.presenters.headLine.HeadLinePresenterImpl
import ru.aston.news.presenters.headLine.HeadLineView

@Module
abstract class PresenterModule {

    @Binds
    abstract fun bindHeadlinePresenter(presenter: HeadLinePresenterImpl): MvpPresenter<HeadLineView>

    @Binds
    abstract fun bindGeneralPresenter(presenter:GeneralPresenter):MvpPresenter<GeneralView>
}