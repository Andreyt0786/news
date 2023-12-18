package ru.aston.news.presenters.headLine

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.aston.news.dto.Post

@StateStrategyType(value = AddToEndStrategy::class)
interface HeadLineView : MvpView {
    fun updateRecycler(posts: List<Post>)

    fun navigToPost(id:Int)
}
