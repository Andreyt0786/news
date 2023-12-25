package ru.aston.news.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.aston.news.App.Companion.router
import ru.aston.news.dto.Post
import ru.aston.news.dto.Screens.BackHeadlineFragment
import ru.aston.news.repository.PostRepository
import javax.inject.Inject

class SingleBusinessViewModel @Inject constructor(
    private val repository: PostRepository,
) : ViewModel() {


    val posts = repository.singleBusinessPost
fun onSetArgs(postId:Int) = repository.getPostById(postId)

    fun navigateBack() {
        router.exit()
    }

    fun like(post: Post) {
        viewModelScope.launch {
            repository.add(post)
        }
    }

    fun remove(post: Post) {
        viewModelScope.launch {
            repository.remove(post)
        }
    }
}