package ru.aston.news.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import kotlinx.coroutines.launch
import ru.aston.news.dto.Post
import ru.aston.news.repository.checkSourceRepository.CheckSourceRepository
import javax.inject.Inject

class CheckSourceViewModel @Inject constructor(
    private val repository: CheckSourceRepository,
) : ViewModel() {

    @Inject
    lateinit var router:Router
    val posts = repository.dataCheckPost

    val listPost = repository.dataPost

    fun loadSourcePost(source: String) = viewModelScope.launch {
        repository.getSourcePost(source)
    }

    fun navigate(screen: FragmentScreen){
        router.navigateTo(screen)
    }

    fun like(post: Post){
        viewModelScope.launch{
            repository.add(post)
        }

    }
}