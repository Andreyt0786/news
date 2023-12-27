package ru.aston.news.uiscreen

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.aston.news.App
import ru.aston.news.R
import ru.aston.news.adapter.sourcePost.InteractionListener
import ru.aston.news.adapter.sourcePost.SourcePostAdapter
import ru.aston.news.databinding.FragmentSourcesBinding
import ru.aston.news.dto.SourcePost
import ru.aston.news.viewModel.SourcePostViewModel
import javax.inject.Inject

class SourceFragment() : Fragment() {
    // @Inject
    //lateinit var router: Router

    @Inject
    lateinit var viewModel: SourcePostViewModel

    private var binding: FragmentSourcesBinding? = null
    private val adapter = SourcePostAdapter(object : InteractionListener {

        override fun showWall(sourcePost: SourcePost) {
            Log.d("SourceFragment", "ShowWall")
            viewModel.navigateToSourceFrag(sourcePost.id, sourcePost.name)
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

        val filters = viewModel.getFilters()

        viewModel.loadSourcePost(filters.language)
        lifecycleScope.launch {
            viewModel.posts.collectLatest {
                adapter.submitList(it)
                binding?.emptyGroup?.isVisible = it.isEmpty()
                binding?.refreshView?.isRefreshing = false
            }
        }

        binding?.refrehButton?.setOnClickListener {
            viewModel.loadSourcePost(filters.language)
        }

        viewModel.state.observe(viewLifecycleOwner) { state ->
            binding?.progress?.isVisible = state.loading
            binding?.refreshView?.isRefreshing = state.refreshing
            binding?.errorGroup?.isVisible = state.error
            if(state.error){
                binding?.emptyGroup?.isVisible = false
            }
           // binding?.emptyGroup?.isVisible = state.emptyGroup
        }

        binding?.refreshView?.setOnRefreshListener {
            viewModel.loadSourcePost(filters.language)
        }

        binding?.topAppBar?.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.filter -> {
                    viewModel.navigateToFilter()
                    true
                }

                R.id.search -> {
                    viewModel.navigateToSearch()
                    true
                }

                else -> false
            }
        }

        binding?.setupRecycler()
    }

    private fun FragmentSourcesBinding.setupRecycler() {
        list.adapter = adapter
    }

}
