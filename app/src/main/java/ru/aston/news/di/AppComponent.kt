
package ru.aston.news.di

import dagger.Component
import ru.aston.news.MainActivity
import ru.aston.news.di.api.ApiModule
import ru.aston.news.di.appModule.AppModule
import ru.aston.news.di.data.DataModule
import ru.aston.news.di.db.DbModule
import ru.aston.news.di.repository.RepositoryModule
import ru.aston.news.uiscreen.BottomNavigationFragment
import ru.aston.news.uiscreen.GeneralBusinessFragment
import javax.inject.Singleton


@Component(
    modules = [
        AppModule::class,
        DataModule::class,
        DbModule::class,
        ApiModule::class,
        RepositoryModule::class]
)
@Singleton
interface AppComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: BottomNavigationFragment)

    fun inject(fragment: GeneralBusinessFragment)
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
