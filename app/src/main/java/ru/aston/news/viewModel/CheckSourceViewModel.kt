package ru.aston.news.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.aston.news.repository.checkSourceRepository.CheckSourceRepository
import javax.inject.Inject

class CheckSourceViewModel @Inject constructor(
    private val repository: CheckSourceRepository,
) : ViewModel() {

    val posts = repository.dataCheckPost

    val listPost = repository.dataPost

    fun loadSourcePost(source: String) = viewModelScope.launch {
        repository.getSourcePost(source)
    }
}