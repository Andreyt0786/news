package ru.aston.news.uiscreen

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.aston.news.App
import ru.aston.news.R
import ru.aston.news.databinding.FragmentFiltersBinding
import ru.aston.news.dto.MainEvent
import ru.aston.news.viewModel.CheckSourceViewModel
import ru.aston.news.viewModel.FilterViewModel
import javax.inject.Inject

class FilterFragment : Fragment() {

    @Inject
    lateinit var viewModel: FilterViewModel

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

        binding?.topAppBar?.setNavigationOnClickListener {
            viewModel.navigateBack()
        }


                    binding!!.toggleGroup.addOnButtonCheckedListener { toggleButton, checkId, isChecked ->

                        if (isChecked) {
                            when (checkId) {
                                R.id.popular -> {
                                    viewModel.send(MainEvent.SaveRelevant("popularity"))
                                    Log.d("LiveData", "relevancy")
                                }

                                R.id.newButton -> {
                                    viewModel.send(MainEvent.SaveRelevant("publishedAt"))
                                    Log.d("LiveData", "new")
                                }

                                R.id.relevant -> {
                                    viewModel.send(MainEvent.SaveRelevant("relevant"))
                                    Log.d("LiveData", "relevancy")
                                }
                            }
                        }
                    }

                    binding!!.radioGroup.setOnCheckedChangeListener { group, checkedId ->

                        when (checkedId) {
                            R.id.rus -> {
                                viewModel.send(MainEvent.SaveLanguage("ru"))
                                Log.d("LiveData", "rus")
                            }

                            R.id.en -> {
                                viewModel.send(MainEvent.SaveLanguage("us"))
                                Log.d("LiveData", "us")
                            }

                            R.id.de -> {
                                viewModel.send(MainEvent.SaveLanguage("de"))
                                Log.d("LiveData", "de")
                            }

                            else -> {
                                viewModel.send(MainEvent.SaveLanguage(null))
                                Log.d("LiveData", "null")
                            }
                        }
                    }

        binding?.topAppBar?.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.checked -> {

                    viewModel.navigateBack()
                    true
                }

                else -> false
            }
        }




        return binding?.root
    }
}
