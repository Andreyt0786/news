package ru.aston.news.presenters.travel

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.aston.news.dto.Post

@StateStrategyType(value = AddToEndStrategy::class)
interface TravelView : MvpView {
    fun updateRecycler(posts: List<Post>)

    fun showProgress()

    fun error()

}