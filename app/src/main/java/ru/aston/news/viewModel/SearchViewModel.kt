package ru.aston.news.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.aston.news.App.Companion.router
import ru.aston.news.model.SearchState
import ru.aston.news.repository.PostRepository
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val repository: PostRepository,
) : ViewModel() {

    private val _state = MutableLiveData(SearchState())
    val state: LiveData<SearchState>
        get() = _state


    val posts = repository.searchPosts

    private var searchJob: Job? = null

    fun clearDB(){
        viewModelScope.launch {
            repository.clearSearchDao()
        }
    }

    fun search(text: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            try {
                Log.d("WARNING2", "called repository.search($text)")
                _state.value = SearchState(loading = true)
                repository.search(text)
                _state.value = SearchState()
            } catch (e: Exception) {
                _state.value = SearchState(error = true)
            }
        }
    }

    fun navigateBack() {
        router.exit()
    }

    fun navigateTo(postId: Int) {

    }


}
