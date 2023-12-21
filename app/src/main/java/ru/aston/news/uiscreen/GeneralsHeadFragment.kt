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
import ru.aston.news.databinding.FragmentHeadGeneralBinding
import ru.aston.news.dto.Post
import ru.aston.news.presenters.general.GeneralPresenter
import ru.aston.news.presenters.general.GeneralView
import javax.inject.Inject


class GeneralsHeadFragment : MvpAppCompatFragment(), GeneralView {

    private var binding: FragmentHeadGeneralBinding? = null
    private val adapter = PostAdapter { post ->
        Log.d("GeneralFragment", "${post.idPost}")
        presenter.navigate(post.idPost)
    }

  /*  @Inject
    lateinit var viewModel: FilterViewModel
*/
    @Inject
    @InjectPresenter
    lateinit var presenter: GeneralPresenter

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
        binding = FragmentHeadGeneralBinding.inflate(inflater, container, false)
        return binding?.root
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val state = presenter.state
        presenter.getData(state.language, state.relevant)
      /*  viewModel.stateFilters.observe(viewLifecycleOwner) { state ->
            Log.d("LiveData", "language = ${state.language}, relevant = ${state.relevant}")
            presenter.getData(state.language, state.relevant)
        }*/

        binding?.setupRecycler()

    }

    private fun FragmentHeadGeneralBinding.setupRecycler() {
        list.adapter = adapter
    }

    override fun updateRecycler(posts: List<Post>) {
        Log.d("WARNING", "posts delivered" + posts.toString())
        adapter.submitList(posts)
    }

}


