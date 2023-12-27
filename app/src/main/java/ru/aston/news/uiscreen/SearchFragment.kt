package ru.aston.news.uiscreen

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.aston.news.App
import ru.aston.news.adapter.post.PostAdapter
import ru.aston.news.databinding.FragmentSearchBinding
import ru.aston.news.viewModel.SearchViewModel
import javax.inject.Inject

class SearchFragment : Fragment() {

    private var binding: FragmentSearchBinding? = null
    private val adapter = PostAdapter { post ->
        Log.d("SearchFragment", "${post.idPost}")
        viewModel.navigateTo(post.idPost)
    }

    @Inject
    lateinit var viewModel: SearchViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding?.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding?.textInput?.editText?.addTextChangedListener {
            adapter.submitList(null)
            viewModel.search(text = it.toString())
        }

        lifecycleScope.launch {
            viewModel.posts.collectLatest {
                adapter.submitList(it)
                Log.d("WARNING2", "posts = $it")
                binding?.refreshView?.isRefreshing = false
                binding?.errorGroup?.isVisible = it.isEmpty()
                }
            }

        viewModel.state.observe(viewLifecycleOwner) { state ->
            binding?.progress?.isVisible = state.loading
            binding?.refreshView?.isRefreshing = state.refreshing
           // binding?.errorGroup?.isVisible = state.error
        }

        binding?.refreshView?.setOnRefreshListener {
            binding?.textInput?.editText?.addTextChangedListener {
                adapter.submitList(null)
                viewModel.search(text = it.toString())
                binding?.refreshView?.isRefreshing = false
            }
        }

        binding?.back?.setOnClickListener {
            adapter.submitList(null)
        }

        binding?.exit?.setOnClickListener {
            adapter.submitList(null)
            viewModel.clearDB()
            viewModel.navigateBack()
        }

        binding?.setupRecycler()
    }

    private fun FragmentSearchBinding.setupRecycler() {
        list.adapter = adapter
    }


}

