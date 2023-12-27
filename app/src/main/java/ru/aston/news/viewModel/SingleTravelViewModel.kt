package ru.aston.news.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.aston.news.App
import ru.aston.news.dto.Post
import ru.aston.news.repository.FiltersRepository
import ru.aston.news.repository.PostRepository
import javax.inject.Inject

class SingleTravelViewModel @Inject constructor(
    private val repository: PostRepository,
    private val filterRepository: FiltersRepository
) : ViewModel() {

    val state = filterRepository.getFilters()

    val posts = repository.singleTravelPost

    //  fun onSetArgs(postId: Int) = repository.getPostById(postId)
    fun navigateBack() {
        App.router.exit()
    }

    fun like(post: Post) {
        viewModelScope.launch {
            repository.add(post)
        }
    }

    fun dislike(post: Post) {
        viewModelScope.launch {
            repository.remove(post)
        }
    }
}