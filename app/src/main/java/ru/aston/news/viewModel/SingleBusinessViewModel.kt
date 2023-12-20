package ru.aston.news.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.aston.news.App.Companion.router
import ru.aston.news.dto.Post
import ru.aston.news.dto.Screens.BackHeadlineFragment
import ru.aston.news.repository.PostRepository
import javax.inject.Inject

class SingleBusinessViewModel @Inject constructor(
   repository: PostRepository,
) : ViewModel() {


    val posts = repository.singleBusinessPost



    fun navigateBack(){
        router.navigateTo(BackHeadlineFragment() )
    }

    fun like(post: Post) {
        viewModelScope.launch {
            //  repository.add(post)
        }

    }
}