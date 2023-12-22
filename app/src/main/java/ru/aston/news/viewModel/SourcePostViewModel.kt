package ru.aston.news.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch
import ru.aston.news.App.Companion.router
import ru.aston.news.dto.Post
import ru.aston.news.dto.Screens
import ru.aston.news.dto.Screens.ForwardCheckSource
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

    fun navigateToSourceFrag(id:String, name:String){
        router.navigateTo(ForwardCheckSource(id, name))
    }

   fun refreshSource() = viewModelScope.launch{
       repository.getSourcePost()
   }


}