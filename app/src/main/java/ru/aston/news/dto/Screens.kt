package ru.aston.news.dto

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.aston.news.uiscreen.BottomNavigationFragment
import ru.aston.news.uiscreen.CheckSourceFragment
import ru.aston.news.uiscreen.FilterFragment
import ru.aston.news.uiscreen.GeneralsHeadFragment
import ru.aston.news.uiscreen.HeadLineFragment
import ru.aston.news.uiscreen.SavedFragment
import ru.aston.news.uiscreen.SearchFragment
import ru.aston.news.uiscreen.SingleBusinessFragment
import ru.aston.news.uiscreen.SingleGeneralFragment
import ru.aston.news.uiscreen.SinglePostFragment
import ru.aston.news.uiscreen.SingleSavedFragment
import ru.aston.news.uiscreen.SingleSearchFragment
import ru.aston.news.uiscreen.SingleTravelFragment

object Screens : Screen {
    fun BottomNavigation() = FragmentScreen {
        BottomNavigationFragment()
    }

    fun ForwardCheckSource(containerId: String, containerName: String) = FragmentScreen {
        CheckSourceFragment.getNewInstance(containerId, containerName)
    }

    fun ForwardSinglePost(title: Int, prevId: String) = FragmentScreen {
        SinglePostFragment.getNewInstance(title, prevId)
    }

    fun BackCheckSource() = FragmentScreen {
        CheckSourceFragment()
    }

    fun ForwardSingleBusinessPost(title: Int) = FragmentScreen {
        SingleBusinessFragment.getNewInstance(title)
    }

    fun ForwardSingleTravelPost(title: Int) = FragmentScreen {
        SingleTravelFragment.getNewInstance(title)
    }

    fun ForwardSingleSearchPost(title: Int) = FragmentScreen {
        SingleSearchFragment.getNewInstance(title)
    }

    fun ForwardSingleGeneralPost(title: Int) = FragmentScreen {
        SingleGeneralFragment.getNewInstance(title)
    }

    fun ForwardFilter() = FragmentScreen {
        FilterFragment()
    }

    fun BackHeadlineFragment() = FragmentScreen {
        HeadLineFragment()
    }


    fun ForwardSingleSavedPost(title: Int) = FragmentScreen {
        SingleSavedFragment.getNewInstance(title)
    }

    fun ForwardSavedPost() = FragmentScreen {
       SavedFragment()
    }

    fun ForwardFilterFragmnet() = FragmentScreen {
       SearchFragment()
    }


}