

package ru.aston.news.presenters.headLine

import io.reactivex.rxjava3.core.Flowable
import moxy.MvpView
import ru.aston.news.entity.PostEntity

interface HeadLinePresenter: MvpView {
    fun callApiPresenter()
    fun getDataFromDatabase() : Flowable<List<PostEntity>>?
    fun onActivityDestroy()
}