package ru.aston.news.uiscreen

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.aston.news.App
import ru.aston.news.adapter.post.PostAdapter
import ru.aston.news.databinding.FragmentSavedBinding
import ru.aston.news.viewModel.SavedViewModel
import javax.inject.Inject

class SavedFragment : Fragment() {
    // @Inject
    //lateinit var router: Router

    @Inject
    lateinit var viewModel: SavedViewModel

    private var binding: FragmentSavedBinding? = null
    private val adapter = PostAdapter { post ->
        Log.d("SavedFragment", "${post.idSaved}")

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


        lifecycleScope.launch {
            viewModel.posts.collectLatest {
                adapter.submitList(it)
            }
        }
        binding?.setupRecycler()
    }

    private fun FragmentSavedBinding.setupRecycler() {
        list.adapter = adapter
    }

}
