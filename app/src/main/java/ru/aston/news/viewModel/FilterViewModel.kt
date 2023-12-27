package ru.aston.news.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.aston.news.App.Companion.router
import ru.aston.news.dto.Filters
import ru.aston.news.dto.MainEvent
import ru.aston.news.repository.FiltersRepository
import javax.inject.Inject

class FilterViewModel @Inject constructor(
    private val repository: FiltersRepository,
) : ViewModel() {

    private val _state = MutableLiveData(repository.getFilters())
    val state: LiveData<Filters?>
        get() = _state


    fun send(event: MainEvent) {
        when (event) {
            is MainEvent.SaveRelevant -> {
                saveRelev(relevant = event.relevant)

            }

            is MainEvent.SaveLanguage -> {
                saveLan(lan = event.language)

            }

            is MainEvent.SaveTime -> {
                saveData(
                    startData = event.startTime,
                    toData = event.endTime,
                    startTime = event.startT,
                    endTime = event.endT
                )
            }
        }
    }

    fun navigateBack() {
        router.exit()
    }

    private fun saveRelev(relevant: String?) {
        repository.saveRelevant(relevant)
        _state.value = _state.value?.copy(relevant = relevant)
    }

    fun saveLan(lan: String?) {
        repository.saveLanguage(lan)
        _state.value = _state.value?.copy(language = lan)

    }

    fun saveData(startData: String?, toData: String?, startTime: String?, endTime: String?) {
        repository.saveData(startData, toData, startTime, endTime)
        _state.value = _state.value?.copy(
            dateFrom = startData,
            dateTo = toData,
            startTime = startTime,
            endTime = endTime,
        )
    }
}

