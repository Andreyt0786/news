package ru.aston.news.presenters.headLine

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndStrategy::class)
interface HeadLineView : MvpView {
    fun invokePresenterToCallApi()

    fun showPost()
}
