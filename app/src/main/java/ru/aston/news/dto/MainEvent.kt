package ru.aston.news.dto

interface MainEvent {
    class SaveRelevant(val relevant: String?) : MainEvent

    class SaveLanguage(val language: String?) : MainEvent

}