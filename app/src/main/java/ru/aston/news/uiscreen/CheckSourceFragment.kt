package ru.aston.news.uiscreen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.aston.news.App
import ru.aston.news.adapter.post.PostAdapter
import ru.aston.news.databinding.FragmentCheckPostBinding
import ru.aston.news.dto.Screens.ForwardSinglePost
import ru.aston.news.viewModel.CheckSourceViewModel
import javax.inject.Inject

class CheckSourceFragment() : Fragment() {

    @Inject
    lateinit var viewModel: CheckSourceViewModel

    private var binding: FragmentCheckPostBinding? = null

    private val title: String
        get() = arguments?.getString(CheckSourceFragment.EXTRA_ID)!!


    private val adapter = PostAdapter { post ->

            viewModel.navigate(post.idPost,title )
        }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheckPostBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireArguments().getString(EXTRA_ID)?.let { viewModel.loadSourcePost(it) }
        lifecycleScope.launch {
            viewModel.posts.collectLatest {
                adapter.submitList(it)
            }
        }
        binding?.setupRecycler()
    }

    private fun FragmentCheckPostBinding.setupRecycler() {
        list.adapter = adapter
    }

    companion object {
        private const val EXTRA_ID = "extra_id"
        private const val EXTRA_NAME = "extra_name"

        fun getNewInstance(id: String?, name: String): CheckSourceFragment {
            return CheckSourceFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_ID, id)
                    putString(EXTRA_NAME, name)
                }
            }
        }
    }

}
