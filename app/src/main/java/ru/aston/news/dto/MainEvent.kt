package ru.aston.news.dto

interface MainEvent {
    class SaveRelevant(val relevant: String?) : MainEvent

    class SaveLanguage(val language: String?) : MainEvent

    class SaveTime(
        val startTime: String?,
        val endTime: String?,
        val startT: String?,
        val endT: String?
    ) : MainEvent

}