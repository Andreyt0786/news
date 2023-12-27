package ru.aston.news.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.aston.news.App
import ru.aston.news.dto.Post
import ru.aston.news.dto.Screens
import ru.aston.news.dto.Screens.ForwardSavedPost
import ru.aston.news.model.SaveModel
import ru.aston.news.model.SourceModelState
import ru.aston.news.repository.PostRepository
import javax.inject.Inject

class SavedViewModel @Inject constructor(
    private val repository: PostRepository,
) : ViewModel() {

    private val _state = MutableLiveData(SaveModel())
    val state: LiveData<SaveModel>
        get() = _state

    val posts = repository.dataSavedPost

    val singlePost = repository.singleSavedPost


    fun delete(countTime:Long){
        repository.deleteOld(countTime)
    }
    fun navigateBack() {
        App.router.navigateTo(ForwardSavedPost())
    }

    fun navigate(title:Int){
        App.router.navigateTo(Screens.ForwardSingleSavedPost(title))

    }
    fun remove(post:Post) {
        viewModelScope.launch {
            repository.remove(post)
        }
    }
    fun like(post: Post) {
        viewModelScope.launch {
            repository.add(post)
        }
    }

    fun navigateToSearch() {
        App.router.navigateTo(Screens.ForwardFilterFragmnet())
    }

    fun navigateToFilter() {
        App.router.navigateTo(Screens.ForwardFilter())
    }

}