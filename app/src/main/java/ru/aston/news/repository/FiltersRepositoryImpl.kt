package ru.aston.news.repository

import ru.aston.news.dao.PostDao
import ru.aston.news.dao.checkSource.CheckSourceDao
import ru.aston.news.dao.general.GeneralDao
import ru.aston.news.dao.savedSource.SavedDao
import ru.aston.news.dao.search.SearchDao
import ru.aston.news.di.api.headLine.ApiBusinessService
import ru.aston.news.dto.Filters
import javax.inject.Inject

class FiltersRepositoryImpl @Inject constructor(
    private val apiService: ApiBusinessService,
    private val dao: PostDao,
    private val checkSourceDao: CheckSourceDao,
    private val generalDao: GeneralDao,
    private val savedDao: SavedDao,
    private val searchDao: SearchDao,
) : FiltersRepository {

    override fun saveRelevant(relevant: String?) {
        filters = filters.copy(relevant = relevant)
    }

    override fun saveLanguage(language: String?) {
        filters = filters.copy(language = language)
    }

    override fun saveData(
        startData: String?,
        toData: String?,
        startTime: String?,
        endTime: String?
    ) {
        filters = filters.copy(
            dateFrom = startData,
            dateTo = toData,
            startTime = startTime,
            endTime = endTime
        )
    }

    override fun getFilters(): Filters = filters

    private var filters: Filters = Filters()
}