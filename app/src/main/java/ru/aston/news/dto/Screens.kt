package ru.aston.news.dto

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.aston.news.uiscreen.BottomNavigationFragment
import ru.aston.news.uiscreen.CheckSourceFragment
import ru.aston.news.uiscreen.SingleBusinessFragment
import ru.aston.news.uiscreen.SingleGeneralFragment
import ru.aston.news.uiscreen.SinglePostFragment

object Screens : Screen {
    fun BottomNavigation() = FragmentScreen {
        BottomNavigationFragment()
    }

    fun ForwardCheckSource(containerId: String, containerName: String) = FragmentScreen {
        CheckSourceFragment.getNewInstance(containerId, containerName)
    }
    fun ForwardSinglePost(title: Int,prevId:String) = FragmentScreen {
        SinglePostFragment.getNewInstance(title, prevId)
    }

    fun BackCheckSource()=FragmentScreen {
        CheckSourceFragment()
    }

    fun ForwardSingleBusinessPost(title: String) = FragmentScreen {
        SingleBusinessFragment.getNewInstance(title)
    }

    fun ForwardSingleGeneralPost(title: Int) = FragmentScreen {
        SingleGeneralFragment.getNewInstance(title)
    }

}