package ru.aston.news.uiscreen

import android.content.Context
import android.icu.util.Calendar
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
import ru.aston.news.adapter.post.PostAdapter
import ru.aston.news.databinding.FragmentSavedBinding
import ru.aston.news.viewModel.SavedViewModel
import javax.inject.Inject

class SavedFragment : Fragment() {

    @Inject
    lateinit var viewModel: SavedViewModel

    private var binding: FragmentSavedBinding? = null
    private val adapter = PostAdapter { post ->
        Log.d("SavedFragment", "${post.idSaved}")
        viewModel.navigate(post.idSaved)
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
        binding = FragmentSavedBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        deleteOldPosts()
        lifecycleScope.launch {
            viewModel.posts.collectLatest {
                adapter.submitList(it)
                Log.d("SaveFragment", "posts = $it")
                binding?.refreshView?.isRefreshing = false
                binding?.errorGroup?.isVisible = it.isEmpty()
            }
        }

        binding?.refreshView?.setOnRefreshListener {
            lifecycleScope.launch {
                viewModel.posts.collectLatest {
                    adapter.submitList(it)
                    binding?.refreshView?.isRefreshing = false
                    binding?.errorGroup?.isVisible = it.isEmpty()
                }
            }
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

    private fun FragmentSavedBinding.setupRecycler() {
        list.adapter = adapter
    }

    private fun deleteOldPosts() {
        val currentTime = System.currentTimeMillis()
        val countTime = currentTime - 1209600000
        viewModel.delete(countTime)
    }
}
