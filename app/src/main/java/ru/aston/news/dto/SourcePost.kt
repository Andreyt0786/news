package ru.aston.news.dto

import com.google.gson.annotations.SerializedName

data class ResponseSource(
    @SerializedName("status") val status: String? = null,
    @SerializedName("sources") val posts: List<SourcePost>? = null
)

data class SourcePost(
    val idPost:Int,
    val id: String,
    val name: String,
    val description: String,
    val url: String,
    val category: String,
    val language: String,
    val country: String?,
) {
    override fun hashCode(): Int = name.hashCode() * 31 + id.hashCode()

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is SourcePost)
            return false
        return name == other.name && id == other.id
    }
}