package ru.aston.news.uiscreen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.aston.news.App
import ru.aston.news.adapter.post.PostAdapter
import ru.aston.news.databinding.FragmentCheckPostBinding
import ru.aston.news.databinding.FragmentFiltersBinding
import ru.aston.news.dto.Screens
import ru.aston.news.viewModel.CheckSourceViewModel
import javax.inject.Inject

class FilterFragment: Fragment() {

    @Inject
    lateinit var viewModel: CheckSourceViewModel

    private var binding: FragmentFiltersBinding? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFiltersBinding.inflate(inflater, container, false)
        return binding?.root
    }
}
