package ru.aston.news.uiscreen

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.aston.news.App
import ru.aston.news.adapter.post.PostAdapter
import ru.aston.news.databinding.FragmentHeadTravelBinding
import ru.aston.news.dto.Post
import ru.aston.news.presenters.general.GeneralView
import ru.aston.news.presenters.travel.TravelPresenter
import ru.aston.news.presenters.travel.TravelView
import javax.inject.Inject


class GeneralTravelFragment : MvpAppCompatFragment(), TravelView {

    private var binding: FragmentHeadTravelBinding? = null
    private val adapter = PostAdapter { post ->
        Log.d("TravelFragment", "${post.idPost}")
        presenter.navigate(post.idPost)
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: TravelPresenter

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
        binding = FragmentHeadTravelBinding.inflate(inflater, container, false)
        return binding?.root
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val filters = presenter.getFilters()
        presenter.getData(filters.language, filters.relevant, filters.dateFrom, filters.dateTo)

        binding?.refreshView?.setOnRefreshListener {
            presenter.getData(filters.language, filters.relevant, filters.dateFrom, filters.dateTo)
        }

        binding?.refrehButton?.setOnClickListener {
            presenter.getData(filters.language, filters.relevant, filters.dateFrom, filters.dateTo)
        }

        binding?.setupRecycler()
    }

    private fun FragmentHeadTravelBinding.setupRecycler() {
        list.adapter = adapter
    }

    override fun updateRecycler(posts: List<Post>) {
        Log.d("WARNING", "posts delivered" + posts.toString())
        adapter.submitList(posts)
        binding?.refreshView?.isRefreshing = false
        binding?.progress?.isVisible = false
        binding?.errorGroup?.isVisible = false
    }

    override fun showProgress() {
        binding?.progress?.isVisible = true
        binding?.errorGroup?.isVisible = false
        binding?.refreshView?.isRefreshing = false
    }

    override fun error() {
        binding?.progress?.isVisible = false
        binding?.errorGroup?.isVisible = true
        binding?.refreshView?.isRefreshing = false
    }
}


