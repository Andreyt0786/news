package ru.aston.news.di.presenter

import dagger.Binds
import dagger.Module
import moxy.MvpPresenter
import ru.aston.news.presenters.general.GeneralPresenter
import ru.aston.news.presenters.general.GeneralView
import ru.aston.news.presenters.headLine.HeadLinePresenterImpl
import ru.aston.news.presenters.headLine.HeadLineView
import ru.aston.news.presenters.travel.TravelPresenter
import ru.aston.news.presenters.travel.TravelView


@Module
abstract class PresenterModule {

    @Binds
    abstract fun bindHeadlinePresenter(presenter: HeadLinePresenterImpl): MvpPresenter<HeadLineView>

    @Binds
    abstract fun bindGeneralPresenter(presenter:GeneralPresenter):MvpPresenter<GeneralView>

    @Binds
    abstract fun bindTravelPresenter(presenter:TravelPresenter):MvpPresenter<TravelView>
}