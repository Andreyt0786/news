package ru.aston.news.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.aston.news.App
import ru.aston.news.dto.Post
import ru.aston.news.dto.Screens
import ru.aston.news.repository.PostRepository
import javax.inject.Inject

class SavedViewModel @Inject constructor(
    private val repository: PostRepository,
) : ViewModel() {


    val posts = repository.dataSavedPost

    val singlePost = repository.singleSavedPost


    fun navigateBack() {
        App.router.navigateTo(Screens.BackHeadlineFragment())
    }

    fun navigate(title:Int){
        App.router.navigateTo(Screens.ForwardSingleSavedPost(title))

    }

    fun like(post: Post) {
        viewModelScope.launch {
            repository.add(post)
        }
    }


}