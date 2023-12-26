package ru.aston.news.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.aston.news.App.Companion.router
import ru.aston.news.dto.Filters
import ru.aston.news.dto.Screens
import ru.aston.news.dto.Screens.ForwardCheckSource
import ru.aston.news.model.SourceModelState
import ru.aston.news.repository.PostRepository
import ru.aston.news.repository.sourcePostRepository.SourcePostRepository
import javax.inject.Inject

class SourcePostViewModel @Inject constructor(
    private val repository: SourcePostRepository,
    private val postRepository: PostRepository
) : ViewModel() {

    private val _state = MutableLiveData(SourceModelState())
    val state: LiveData<SourceModelState>
        get() = _state

    val posts = repository.dataSourcePost

    fun loadSourcePost(language:String?) = viewModelScope.launch {
        try {
            _state.value = SourceModelState(loading = true)
            repository.getSourcePost(language)
            _state.value = SourceModelState()
        } catch (e: Exception) {
            _state.value = SourceModelState(error = true)
        }
    }


    fun getFilters(): Filters = postRepository.getFilters()
    fun navigateToSourceFrag(id: String, name: String) {
        router.navigateTo(ForwardCheckSource(id, name))
    }

    fun navigateToSearch() {
        router.navigateTo(Screens.ForwardFilterFragmnet())
    }

    fun navigateToFilter() {
        router.navigateTo(Screens.ForwardFilter())
    }

}