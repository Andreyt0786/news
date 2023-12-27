package ru.aston.news.repository

import ru.aston.news.dto.Filters

interface FiltersRepository {

    fun getFilters(): Filters

    fun saveRelevant(relevant: String?)

    fun saveLanguage(language: String?)

    fun saveData(
        startData: String?,
        toData: String?,
        startTime: String?,
        endTime: String?
    )
}