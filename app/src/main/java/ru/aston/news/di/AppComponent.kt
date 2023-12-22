package ru.aston.news.di

import dagger.Component
import ru.aston.news.MainActivity
import ru.aston.news.di.api.ApiModule
import ru.aston.news.di.appModule.AppModule
import ru.aston.news.di.data.DataModule
import ru.aston.news.di.db.DbModule
import ru.aston.news.di.navigation.NavigationModule
import ru.aston.news.di.presenter.PresenterModule
import ru.aston.news.di.repository.RepositoryModule
import ru.aston.news.di.viewModel.ViewModelModule
import ru.aston.news.uiscreen.BottomNavigationFragment
import ru.aston.news.uiscreen.CheckSourceFragment
import ru.aston.news.uiscreen.FilterFragment
import ru.aston.news.uiscreen.GeneralBusinessFragment
import ru.aston.news.uiscreen.GeneralsHeadFragment
import ru.aston.news.uiscreen.SavedFragment
import ru.aston.news.uiscreen.SingleBusinessFragment
import ru.aston.news.uiscreen.SingleGeneralFragment
import ru.aston.news.uiscreen.SinglePostFragment
import ru.aston.news.uiscreen.SingleSavedFragment
import ru.aston.news.uiscreen.SourceFragment
import javax.inject.Singleton


@Component(
    modules = [
        AppModule::class,
        DataModule::class,
        DbModule::class,
        ApiModule::class,
        RepositoryModule::class,
        NavigationModule::class,
        PresenterModule::class,
        ViewModelModule::class,
    ]
)
@Singleton
interface AppComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: BottomNavigationFragment)

    fun inject(fragment: GeneralBusinessFragment)

    fun inject(fragment: GeneralsHeadFragment)

    fun inject(fragment: SourceFragment)

    fun inject(fragment:CheckSourceFragment)

    fun inject(fragment:SinglePostFragment)

    fun inject(fragment: SingleBusinessFragment)

    fun inject(fragment: SingleGeneralFragment)

    fun inject(fragment: FilterFragment)

    fun inject(fragment: SavedFragment)

    fun inject(fragment: SingleSavedFragment)
}
/*
    fun inject(activity: MainActivity)

    fun inject(fragment: SampleFragment)

    fun inject(activity: BottomNavigationActivity)

    fun inject(fragment: TabContainerFragment)

    fun inject(fragment: ProfileFragment)

    fun inject(fragment: SelectPhotoFragment)

    fun inject(activity: ProfileActivity)

    fun inject(fragment: SemiTransparentFragment)


}


     */
