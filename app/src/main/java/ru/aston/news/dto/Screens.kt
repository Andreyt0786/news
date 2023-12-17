package ru.aston.news.dto

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.aston.news.uiscreen.BottomNavigationFragment
import ru.aston.news.uiscreen.CheckSourceFragment
import ru.aston.news.uiscreen.HeadLineFragment
import ru.aston.news.uiscreen.SavedFragment
import ru.aston.news.uiscreen.SinglePostFragment
import ru.aston.news.uiscreen.SourceFragment

object Screens {
    fun BottomNavigation() = FragmentScreen {
        BottomNavigationFragment()
    }

    fun ForwardCheckSource(containerId: String, containerName: String) = FragmentScreen {
        CheckSourceFragment.getNewInstance(containerId, containerName)
    }
    fun ForwardSinglePost(title: String) = FragmentScreen {
        SinglePostFragment.getNewInstance(title)
    }
}