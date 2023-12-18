package ru.aston.news.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import kotlinx.coroutines.launch
import ru.aston.news.App
import ru.aston.news.App.Companion.router
import ru.aston.news.dto.Post
import ru.aston.news.repository.generalRepository.GeneralRepository
import javax.inject.Inject

class SingleGeneralViewModel @Inject constructor(
    private val repository: GeneralRepository,
) : ViewModel() {


    val posts = repository.singlePost

    fun navigate(screen: FragmentScreen){
        router.navigateTo(screen)
    }

    fun like(post: Post){
        viewModelScope.launch{
          //  repository.add(post)
        }

    }
}