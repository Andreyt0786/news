package ru.aston.news.viewModel

import androidx.lifecycle.ViewModel
import ru.aston.news.App.Companion.router
import ru.aston.news.dto.Screens
import ru.aston.news.repository.PostRepository
import javax.inject.Inject

class HeadLineViewModel @Inject constructor(
    private val repository: PostRepository,
) : ViewModel() {

    fun navigateToSearch() {
        router.navigateTo(Screens.ForwardFilterFragmnet())
    }

    fun navigateToFilter() {
        router.navigateTo(Screens.ForwardFilter())
    }
}