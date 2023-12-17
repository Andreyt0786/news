package ru.aston.news.presenters.activityView

import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter

import ru.aston.news.dto.Screens.BottomNavigation

@InjectViewState
class ActivityPresenter (private val router: Router):MvpPresenter<ActivityView>() {

   /* override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.newRootScreen(BottomNavigation())
    }*/
    fun onBackPressed(){
        router.exit()
    }

}