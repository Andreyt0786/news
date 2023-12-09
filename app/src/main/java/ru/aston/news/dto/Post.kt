package ru.aston.news.dto

data class Post(
    val idPost:Long,
    val source :Source,
    val author: String?,
    val title: String,
    val desription:String?,
    val url: String,
    val urlToImage:String?,
    val publishedAt:String,
    val content:String?,
)
data class Source(
    val id:String?,
    val name:String
)
