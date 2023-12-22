package ru.aston.news.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.aston.news.App
import ru.aston.news.dto.Filters
import ru.aston.news.dto.MainEvent
import ru.aston.news.dto.Screens
import ru.aston.news.repository.PostRepository
import javax.inject.Inject

class FilterViewModel @Inject constructor(
    private val repository: PostRepository,
) : ViewModel() {

   private val _stateLanguage = MutableLiveData<String?>()
    val stateLanguage: LiveData<String?>
        get() = _stateLanguage


    fun send(event: MainEvent) {
        when (event) {
            is MainEvent.SaveRelevant -> {
                saveRelev(relevant = event.relevant)

            }

            is MainEvent.SaveLanguage -> {
                saveLan(lan = event.language)

            }
        }
    }

    fun navigateBack(){
        App.router.navigateTo(Screens.BackHeadlineFragment())
    }

    private fun saveRelev(relevant: String?) {
        repository.saveRelevant(relevant)
       // _state.value = Filters(relevant = relevant, language = _state.value!!.language)
    }

    fun saveLan(lan: String?) {
        repository.saveLanguage(lan)
       // _state.value = Filters(relevant = _state.value!!.relevant, language = lan)
        _stateLanguage.value = lan
    }
}

