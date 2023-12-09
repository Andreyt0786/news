package ru.aston.news.uiscreen


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import ru.aston.news.App
import ru.aston.news.R
import ru.aston.news.databinding.FragmentBottomNavigationBinding

class BottomNavigationFragment : Fragment() {

  /*  @InjectPresenter
    lateinit var presenter: ActivityPresenter

    private val navigator = AppNavigator(this, R.id.nav_container)
    @ProvidePresenter
    fun createActivityPresenter() = ActivityPresenter(router)*/

    private companion object {
        const val HEADLINES_TAG = "HEADLINES_TAG"
        const val SAVED_TAG = "SAVED_TAG"
        const val SOURCES_TAG = "SOURCES_TAG"
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

       App.appComponent.inject(this)
      val binding = FragmentBottomNavigationBinding.inflate(inflater, container, false)

        // Стартовая вкладка – Feed, если фрагментов нет
        if (childFragmentManager.findFragmentById(R.id.container) == null) {
            loadFragment(HEADLINES_TAG, ::HeadLineFragment)
        }

        binding.bottomNavigation.setOnItemSelectedListener {
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




        return binding.root
    }

    /**
     * Создаём фрагменты лениво и переиспользуем старые
     */
    private inline fun loadFragment(tag: String, fragmentFactory: () -> Fragment) {
        // Фрагмент, к которому мы хотим перейти. Его кешированная версия
        val cachedFragment = childFragmentManager.findFragmentByTag(tag)
        // Фрагмент, который сейчас на экране
        val currentFragment = childFragmentManager.findFragmentById(R.id.container)

        // При повторной навигации ничего не делаем
        if (currentFragment?.tag == tag) return

        childFragmentManager.commit {
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
