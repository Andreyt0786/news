package ru.aston.news.uiscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import ru.aston.news.R
import ru.aston.news.adapter.pager.NumberAdapter
import ru.aston.news.databinding.FragmentHeadlinesBinding

class HeadLineFragment : Fragment() {

    private lateinit var adapter: NumberAdapter

    private val fragList = listOf(
        GeneralsHeadFragment(),//(.newInstance()),
        GeneralBusinessFragment(),
        GeneralTravelFragment(),
    )

    private val fragitems = listOf(
        "General",
        "Business",
        "Traveling"
    )

    private val fragmentPicture = listOf(
        R.drawable.general,
        R.drawable.business,
        R.drawable.traveling
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHeadlinesBinding.inflate(inflater, container, false)


        adapter = NumberAdapter(this, fragList as List<Fragment>)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
            tab.text = fragitems[pos]
            tab.setIcon(fragmentPicture[pos])
        }.attach()

        return binding.root
    }
}





