package ru.aston.news.dto

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.aston.news.uiscreen.BottomNavigationFragment
import ru.aston.news.uiscreen.CheckSourceFragment

object Screens {
    fun BottomNavigation() = FragmentScreen {
       BottomNavigationFragment()
    }

    fun ForwardCheckSource(containerId: String, containerName: String) = FragmentScreen {
        CheckSourceFragment.getNewInstance(containerId, containerName)
    }
}