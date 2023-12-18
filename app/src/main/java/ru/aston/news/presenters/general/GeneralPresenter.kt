package ru.aston.news.presenters.general

import android.util.Log
import com.github.terrakok.cicerone.androidx.FragmentScreen
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.aston.news.App
import ru.aston.news.DisposableManager
import ru.aston.news.repository.generalRepository.GeneralRepository
import javax.inject.Inject


@InjectViewState
class GeneralPresenter @Inject constructor(
    private val generalRepository: GeneralRepository
):MvpPresenter<GeneralView>() {


    val TAG = GeneralPresenter::class.java.simpleName

    fun getData() {
        DisposableManager.add(
            generalRepository.getPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ response ->
                    viewState.updateRecycler(response)
                }, {
                    Log.e(TAG, "error = $it")
                })
        )
    }

    fun navigate(screen: FragmentScreen){
        App.router.navigateTo(screen)
    }
}