package ru.aston.news.dto

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.aston.news.uiscreen.BottomNavigationFragment

object Screens {
    fun BottomNavigation() = FragmentScreen {
       BottomNavigationFragment()
    }
}