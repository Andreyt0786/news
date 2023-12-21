package ru.aston.news.presenters.general

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.aston.news.App.Companion.router
import ru.aston.news.DisposableManager
import ru.aston.news.dto.Screens.ForwardSingleGeneralPost
import ru.aston.news.repository.PostRepository
import javax.inject.Inject


@InjectViewState
class GeneralPresenter @Inject constructor(
    private val generalRepository: PostRepository
):MvpPresenter<GeneralView>() {

    val state = generalRepository.filtersState

    val TAG = GeneralPresenter::class.java.simpleName

    fun getData(language:String?,sortBy:String?) {
        DisposableManager.add(
            generalRepository.getGeneralPosts(language,sortBy)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ response ->
                    viewState.updateRecycler(response)
                }, {
                    Log.e(TAG, "error = $it")
                })
        )
    }

    fun navigate(title:Int){
       router.navigateTo(ForwardSingleGeneralPost(title))

    }
}