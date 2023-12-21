package ru.aston.news.dto

data class Filters(
    val relevant:String? = null,
    val language:String? = null,
   // val dateFrom:DateTime?=null,
    //val dateTo: DateTime?=null
)

enum class Relevant{
   POPULAR,NEW,RELEVANT
}

enum class Lang{
    RUS,DE,US
}