package ru.aston.news.presenters.general

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.aston.news.App.Companion.router
import ru.aston.news.DisposableManager
import ru.aston.news.dto.Filters
import ru.aston.news.dto.Screens
import ru.aston.news.dto.Screens.ForwardSingleBusinessPost
import ru.aston.news.dto.Screens.ForwardSingleGeneralPost
import ru.aston.news.model.GeneralModelState
import ru.aston.news.repository.PostRepository
import javax.inject.Inject


@InjectViewState
class GeneralPresenter @Inject constructor(
    private val generalRepository: PostRepository
) : MvpPresenter<GeneralView>() {

    val TAG = GeneralPresenter::class.java.simpleName
    private val _state = MutableLiveData(GeneralModelState())
    val state: LiveData<GeneralModelState>
    get() = _state

    fun getData(language: String?, sortBy: String?, from: String?, to: String?) {
        viewState.showProgress()
        DisposableManager.add(
            generalRepository.getGeneralPosts(language, sortBy, from, to)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ response ->
                   // if(!response.isNullOrEmpty()) {
                        viewState.updateRecycler(response)
                   // } else{
                    //    viewState.error()
                  //  }
                }, {
                    Log.e(TAG, "error = $it")
                })
        )
    }

    fun getFilters(): Filters = generalRepository.getFilters()


    fun navigate(id: Int) {
        router.navigateTo(ForwardSingleGeneralPost(id))
    }

}