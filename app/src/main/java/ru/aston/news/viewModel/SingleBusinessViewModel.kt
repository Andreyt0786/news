package ru.aston.news.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.androidx.FragmentScreen
import kotlinx.coroutines.launch
import ru.aston.news.App
import ru.aston.news.dto.Post
import ru.aston.news.repository.PostRepository
import javax.inject.Inject

class SingleBusinessViewModel @Inject constructor(
    private val repository: PostRepository,
) : ViewModel() {

    val posts = repository.singlePost

    fun navigate(screen: FragmentScreen) {
        App.router.navigateTo(screen)
    }

    fun like(post: Post) {
        viewModelScope.launch {
            //  repository.add(post)
        }

    }
}