package ru.aston.news.presenters.headLine

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.aston.news.App.Companion.router
import ru.aston.news.DisposableManager
import ru.aston.news.dto.Screens.BackBusinessFragment
import ru.aston.news.dto.Screens.ForwardSingleBusinessPost
import ru.aston.news.repository.PostRepository
import javax.inject.Inject

@InjectViewState
class HeadLinePresenterImpl @Inject constructor(
    private val repository: PostRepository
) : MvpPresenter<HeadLineView>() {
    val TAG = HeadLinePresenterImpl::class.java.simpleName

    val state = repository.filtersState
    fun navigate(id: Int) {
        router.navigateTo(ForwardSingleBusinessPost(id))
    }

    fun getData() {
        DisposableManager.add(
            repository.getBusinessPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ response ->
                    viewState.updateRecycler(response)
                }, {
                    Log.e(TAG, "error = $it")
                })
        )
    }

}

