package ru.aston.news.uiscreen

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.aston.news.App
import ru.aston.news.adapter.post.OnInteractionListener
import ru.aston.news.adapter.sourcePost.InteractionListener
import ru.aston.news.adapter.sourcePost.SourcePostAdapter
import ru.aston.news.databinding.FragmentHeadBusinessBinding
import ru.aston.news.databinding.FragmentSourcesBinding
import ru.aston.news.dto.Post
import ru.aston.news.dto.Screens.ForwardCheckSource
import ru.aston.news.dto.SourcePost
import ru.aston.news.presenters.headLine.HeadLinePresenterImpl
import ru.aston.news.viewModel.SourcePostViewModel
import javax.inject.Inject

class SourceFragment() : Fragment() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var viewModel: SourcePostViewModel

    private var binding: FragmentSourcesBinding? = null
    private val adapter = SourcePostAdapter(object : InteractionListener {

        override fun showWall(sourcePost: SourcePost) {
            Log.d("SourceFragment", "ShowWall")
            router.navigateTo(ForwardCheckSource(sourcePost.id, sourcePost.name))
        }
    })


    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSourcesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadSourcePost()
        lifecycleScope.launch {
            viewModel.posts.collectLatest {
                adapter.submitList(it)
            }
        }
        binding?.setupRecycler()
    }

    private fun FragmentSourcesBinding.setupRecycler() {
        list.adapter = adapter
    }

}
