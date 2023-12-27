package ru.aston.news.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.aston.news.App.Companion.router
import ru.aston.news.dto.Post
import ru.aston.news.repository.FiltersRepository
import ru.aston.news.repository.PostRepository
import javax.inject.Inject

class SingleGeneralViewModel @Inject constructor(
    private val repository: PostRepository,
    private val filterRepository: FiltersRepository
) : ViewModel() {

    val state = filterRepository.getFilters()

    val posts = repository.singleGeneralPost

    //  fun onSetArgs(postId: Int) = repository.getPostById(postId)
    fun navigateBack() {
        router.exit()
    }

    fun like(post: Post) {
        viewModelScope.launch {
            repository.add(post)
        }
        repository.addGeneral(post)
    }

    fun dislike(post: Post) {
        viewModelScope.launch {
            repository.remove(post)
        }
        repository.removeGeneral(post)
    }
}