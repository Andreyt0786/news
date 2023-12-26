package ru.aston.news.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.aston.news.App.Companion.router
import ru.aston.news.dto.Post
import ru.aston.news.dto.Screens.ForwardCheckSource
import ru.aston.news.dto.Screens.ForwardSinglePost
import ru.aston.news.model.CheckState
import ru.aston.news.repository.PostRepository
import javax.inject.Inject

class CheckSourceViewModel @Inject constructor(
    private val repository: PostRepository,
) : ViewModel() {

    private val _state = MutableLiveData(CheckState())
    val state: LiveData<CheckState>
        get() = _state

    val posts = repository.dataCheckPost

    val listPost = repository.dataPost


    fun loadSourcePost(source: String) =
        viewModelScope.launch {
            try {
                _state.value = CheckState(loading = true)
                repository.getSourcePost(source)
                _state.value = CheckState()
            } catch (e: Exception) {
                _state.value = CheckState(error = true)
            }
        }

    fun navigate(id: Int, title: String) {
        router.navigateTo(ForwardSinglePost(id, title))
    }

    fun navigateBacktoSource() {
        router.exit()
    }

    fun navigatetoSource(id: String, name: String) {
        router.navigateTo(ForwardCheckSource(id, name))
    }

    fun like(post: Post) {
        viewModelScope.launch {
            repository.add(post)
        }

    }
}