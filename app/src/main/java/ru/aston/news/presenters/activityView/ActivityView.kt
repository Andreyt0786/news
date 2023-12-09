package ru.aston.news.presenters.activityView

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndStrategy::class)
interface ActivityView : MvpView {
    fun onBackPressed()
}