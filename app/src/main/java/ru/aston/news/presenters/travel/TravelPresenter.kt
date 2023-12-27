package ru.aston.news.presenters.travel

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.aston.news.App
import ru.aston.news.DisposableManager
import ru.aston.news.dto.Filters
import ru.aston.news.dto.Screens
import ru.aston.news.presenters.general.GeneralView
import ru.aston.news.repository.FiltersRepository
import ru.aston.news.repository.PostRepository
import javax.inject.Inject


@InjectViewState
class TravelPresenter @Inject constructor(
    private val generalRepository: PostRepository,
    private val filterRepository: FiltersRepository
) : MvpPresenter<TravelView>() {

    val TAG = TravelPresenter::class.java.simpleName


    fun getData(language: String?, sortBy: String?, from: String?, to: String?) {
        viewState.showProgress()
        DisposableManager.add(
            generalRepository.getTravelPosts(language, sortBy, from, to)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ response ->
                    if (!response.isNullOrEmpty()) {
                        viewState.updateRecycler(response)
                    } else {
                        viewState.error()
                    }
                }, {
                    Log.e(TAG, "error = $it")
                })
        )
    }

    fun getFilters(): Filters = filterRepository.getFilters()


    fun navigate(id: Int) {
        App.router.navigateTo(Screens.ForwardSingleTravelPost(id))
    }

}