package ru.aston.news

import android.os.Bundle
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.aston.news.App.Companion.navigatorHolder
import ru.aston.news.App.Companion.router
import ru.aston.news.presenters.activityView.ActivityPresenter
import ru.aston.news.presenters.activityView.ActivityView
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), ActivityView {

//Внедрение презентера
    @InjectPresenter
    lateinit var presenter: ActivityPresenter

    private val navigator = AppNavigator(this, R.id.nav_container)

    //добавление в конструктор презентера параметров
    @ProvidePresenter
    fun createActivityPresenter() = ActivityPresenter(router)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

      //dagger
      //(applicationContext as App).appComponent.inject(this)
        App.appComponent.inject(this)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        presenter.onBackPressed()
    }
}

