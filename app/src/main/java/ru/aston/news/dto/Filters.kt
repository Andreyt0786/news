package ru.aston.news.dto

data class Filters(
    val relevant: String? = null,
    val language: String? = null,
    val dateFrom: String? = null,
    val dateTo: String? = null
)

enum class Relevant(val value: String) {
    POPULAR("popularity"),
    NEW("publishedAt"),
    RELEVANT("relevant")
}

enum class Lang(val value: String) {
    RU("ru"),
    DE("de"),
    EN("en")
}