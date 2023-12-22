package ru.aston.news.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.aston.news.App.Companion.router
import ru.aston.news.dto.Post
import ru.aston.news.dto.Screens
import ru.aston.news.dto.Screens.BackHeadlineFragment
import ru.aston.news.dto.Screens.ForwardCheckSource
import ru.aston.news.dto.Screens.ForwardSinglePost
import ru.aston.news.repository.PostRepository
import javax.inject.Inject

class CheckSourceViewModel @Inject constructor(
    private val repository: PostRepository,
) : ViewModel() {


    val posts = repository.dataCheckPost

    val listPost = repository.dataPost

    fun loadSourcePost(source: String) = viewModelScope.launch {
        repository.getSourcePost(source)
    }

    fun navigate(id: Int, title: String) {
        router.navigateTo(ForwardSinglePost(id, title))
    }

    fun navigateBacktoSource(){
        router.navigateTo(BackHeadlineFragment())
    }
    fun navigatetoSource(id:String, name:String){
        router.navigateTo(ForwardCheckSource(id, name))
    }

    fun like(post: Post) {
        viewModelScope.launch {
            repository.add(post)
        }

    }
}