package ru.aston.news.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.aston.news.App.Companion.router
import ru.aston.news.repository.PostRepository
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val repository: PostRepository,
) : ViewModel() {

    val posts = repository.searchPosts

    private var searchJob: Job? = null

    fun search(text: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            Log.d("WARNING2", "called repository.search($text)")
            repository.search(text)
        }
    }

    fun navigateBack() {
        router.exit()
    }

    fun navigateTo(postId:Int){

    }


}
