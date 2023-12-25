package ru.aston.news.model

data class GeneralModel(
    val loading: Boolean = false,
    val error: Boolean = false,
    val refreshing: Boolean = false,
)
