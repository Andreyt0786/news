package ru.aston.news.uiscreen

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.aston.news.App
import ru.aston.news.adapter.post.PostAdapter
import ru.aston.news.databinding.FragmentHeadBusinessBinding
import ru.aston.news.databinding.FragmentHeadGeneralBinding
import ru.aston.news.dto.Post
import ru.aston.news.presenters.headLine.HeadLinePresenterImpl
import ru.aston.news.presenters.headLine.HeadLineView
import javax.inject.Inject

class GeneralBusinessFragment : MvpAppCompatFragment(), HeadLineView {

    private var binding: FragmentHeadBusinessBinding? = null

    private val adapter = PostAdapter { post ->
        Log.d("BusinessFragment", "${post.idPost}")
        presenter.navigate(post.idPost)
    }


    @Inject
    @InjectPresenter
    lateinit var presenter: HeadLinePresenterImpl

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeadBusinessBinding.inflate(inflater, container, false)
        return binding?.root
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val filters = presenter.getFilters()
        presenter.getData(filters.language, filters.relevant,filters.dateFrom,filters.dateTo)

        binding?.refreshView?.setOnRefreshListener {
            presenter.getData(filters.language, filters.relevant,filters.dateFrom,filters.dateTo)
        }
        binding?.setupRecycler()
    }

    private fun FragmentHeadBusinessBinding.setupRecycler() {
        list.adapter = adapter
    }

    override fun updateRecycler(posts: List<Post>) {
        Log.d("WARNING", "posts delivered" + posts.toString())
        adapter.submitList(posts)
        binding?.refreshView?.isRefreshing = false
    }

    override fun navigToPost(id: Int) {

    }
}



