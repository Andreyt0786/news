
package ru.aston.news.uiscreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.aston.news.App
import ru.aston.news.adapter.post.OnInteractionListener
import ru.aston.news.adapter.post.PostAdapter
import ru.aston.news.dao.PostDao
import ru.aston.news.databinding.FragmentHeadBusinessBinding
import ru.aston.news.di.api.ApiBusinessService
import ru.aston.news.presenters.headLine.HeadLinePresenterImpl
import ru.aston.news.presenters.headLine.HeadLineView
import javax.inject.Inject

class GeneralBusinessFragment : MvpAppCompatFragment(), HeadLineView {

   @InjectPresenter
    lateinit var presenter: HeadLinePresenterImpl


    //добавление в конструктор презентера параметров
    @ProvidePresenter
    fun createHeadLinePresenter():HeadLinePresenterImpl {
        return HeadLinePresenterImpl(apiBusinessService, postDao, App.router)
    }

    @Inject
    lateinit var apiBusinessService: ApiBusinessService

    @Inject
    lateinit var postDao: PostDao


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        createHeadLinePresenter()
        App.appComponent.inject(this)
        val binding = FragmentHeadBusinessBinding.inflate(inflater, container, false)


        invokePresenterToCallApi()

        val adapter = PostAdapter(object : OnInteractionListener {})
        binding.list.adapter = adapter
        presenter.getDataFromDatabase()?.let {
            presenter.getDataFromDatabase()!!.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { posts ->
                        adapter.submitList(posts)
                    },
                    {
                            error -> Log.e("TAG", "error.message")
                    }
                )
        }

        return binding.root
    }

    override fun invokePresenterToCallApi() {
        presenter.callApiPresenter()
    }

    override fun showPost() {
      presenter.getDataFromDatabase()
    }


}


