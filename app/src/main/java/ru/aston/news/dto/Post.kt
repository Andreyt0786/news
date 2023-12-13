package ru.aston.news.dto

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("status") val status: String? = null,
    @SerializedName("totalResults") val totalResults: Int? = null,
    @SerializedName("articles") val posts: List<Post>? = null
)


data class Post(
    val idPost:Int,
    val source :Source,
    val author: String?,
    val title: String,
    val description:String?,
    val url: String,
    val urlToImage:String?,
    val publishedAt:String,
    val content:String?,
)
data class Source(
    val id:String?,
    val name:String
)
