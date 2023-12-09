package ru.aston.news.presenters.headLine

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.aston.news.DisposableManager
import ru.aston.news.dao.PostDao
import ru.aston.news.di.api.ApiBusinessService
import ru.aston.news.dto.Post
import ru.aston.news.entity.PostEntity
import javax.inject.Inject

@InjectViewState
class HeadLinePresenterImpl @Inject constructor(
    private val apiBusinessService: ApiBusinessService,
    private val postDao: PostDao,
    private val router: Router,
) : MvpPresenter<HeadLineView>() {
    val TAG = HeadLinePresenterImpl::class.java.simpleName

    fun callApiPresenter() {
        DisposableManager.add(
            apiBusinessService.getAll()
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { posts ->
                        Log.i(TAG, "from api----->\n" + posts.toString())

                        /*val dbArticleList: MutableList<PostEntity> = ArrayList()

                        for (article in articleResponseModel.response.docs) {

                            if(!article.multimedia.isNullOrEmpty()) {
                                dbArticleList.add(
                                    ArticleDBModel(
                                        null, article.snippet
                                        , "https://www.nytimes.com/" + article.multimedia[0].url
                                    )
                                )
                            }

                        }*/
                       // postDao.insert(posts.toEntity())

                        /* RxJavaRetrofitRoomSampleApplication.database?.let {
                             it.getArticleDao().deleteAll()
                             it.getArticleDao().insertAll(dbArticleList)
                         }*/
                    },
                    { error -> error.message?.let { Log.e(TAG, it) } }
                )
        )
    }


    fun getDataFromDatabase(): Flowable<List<Post>>? {
        return postDao.getAll()
            .map {it.map(PostEntity::toDto)}
            //.switchMap { data -> Flowable.just(data) }
        /*RxJavaRetrofitRoomSampleApplication.database?.let {
                it.getArticleDao().getArticles().switchMap { data -> Flowable.just(data) }*/
    }


    fun onActivityDestroy() {
        DisposableManager.dispose()
    }

}
