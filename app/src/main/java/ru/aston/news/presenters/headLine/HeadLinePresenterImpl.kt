package ru.aston.news.presenters.headLine

import android.util.Log
import com.github.terrakok.cicerone.androidx.FragmentScreen
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.aston.news.App
import ru.aston.news.DisposableManager
import ru.aston.news.repository.PostRepository
import ru.aston.news.uiscreen.SingleBusinessFragment
import javax.inject.Inject

@InjectViewState
class HeadLinePresenterImpl @Inject constructor(
   private val repository: PostRepository
) : MvpPresenter<HeadLineView>() {
    val TAG = HeadLinePresenterImpl::class.java.simpleName

    fun navigate(screen: FragmentScreen){
        App.router.navigateTo(screen)
    }
    fun getData() {
        DisposableManager.add(
            repository.getPosts()
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

