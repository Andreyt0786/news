package ru.aston.news.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.aston.news.dto.SourcePost
import ru.aston.news.repository.sourcePostRepository.SourcePostRepository
import javax.inject.Inject

class SourcePostViewModel @Inject constructor(
    private val repository: SourcePostRepository,
):ViewModel() {

    val posts = repository.dataSourcePost

    fun loadSourcePost()=viewModelScope.launch{
        repository.getSourcePost()
    }
}