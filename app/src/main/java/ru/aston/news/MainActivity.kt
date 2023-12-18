package ru.aston.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.bottomnavigation.BottomNavigationView
import moxy.MvpAppCompatActivity
import ru.aston.news.App.Companion.navigatorHolder
import ru.aston.news.presenters.activityView.ActivityView
import ru.aston.news.uiscreen.HeadLineFragment
import ru.aston.news.uiscreen.SavedFragment
import ru.aston.news.uiscreen.SourceFragment
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), ActivityView {

    private companion object {
        const val HEADLINES_TAG = "HEADLINES_TAG"
        const val SAVED_TAG = "SAVED_TAG"
        const val SOURCES_TAG = "SOURCES_TAG"
    }



    private lateinit var bottomNavigationBar: BottomNavigationView

    private val navigator = AppNavigator(this, R.id.container)

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)


        App.appComponent.inject(this)

        bottomNavigationBar = findViewById<View>(R.id.bottom_navigation) as BottomNavigationView
        if (supportFragmentManager.findFragmentById(R.id.container) == null) {
            loadFragment(HEADLINES_TAG, ::HeadLineFragment)
        }
        bottomNavigationBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.headlines -> {
                    loadFragment(HEADLINES_TAG, ::HeadLineFragment)
                    true
                }

                R.id.favourites -> {
                    loadFragment(SAVED_TAG, ::SavedFragment)
                    true
                }

                R.id.sources -> {
                    loadFragment(SOURCES_TAG, ::SourceFragment)
                    true
                }

                else -> false
            }
        }
    }


        override fun onResumeFragments() {
            super.onResumeFragments()
            navigatorHolder.setNavigator(navigator)
        }

        override fun onPause() {
            navigatorHolder.removeNavigator()
            super.onPause()
        }
    private inline fun loadFragment(tag: String, fragmentFactory: () -> Fragment) {
        // Фрагмент, к которому мы хотим перейти. Его кешированная версия
        val cachedFragment = supportFragmentManager.findFragmentByTag(tag)
        // Фрагмент, который сейчас на экране
        val currentFragment = supportFragmentManager.findFragmentById(R.id.container)

        // При повторной навигации ничего не делаем
        if (currentFragment?.tag == tag) return

        supportFragmentManager.commit {
            if (currentFragment != null) {
                // Старый фрагмент не теряем, а откладываем
                detach(currentFragment)
            }
            if (cachedFragment != null) {
                // Цепляем старый
                attach(cachedFragment)
            } else {
                // Создаём новый и присваиваем ему тег
                replace(R.id.container, fragmentFactory(), tag)
            }
        }
    }
}
