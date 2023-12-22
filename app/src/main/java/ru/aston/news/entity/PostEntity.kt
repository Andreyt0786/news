package ru.aston.news.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.aston.news.dto.Post
import ru.aston.news.dto.Source

@Entity
data class PostEntity(
    val idSaved: Int,
    @PrimaryKey(autoGenerate = true)
    val idPost: Int,
    @Embedded
    val source: Source,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?,
    val isLiked: Boolean,

    ) {

    fun toDto() = Post(
        idSaved = idSaved,
        idPost = idPost.hashCode(),
        source = source,
        author = author,
        title = title,
        description = description,
        content = content,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        isLiked = isLiked,
    )

    companion object {
        fun fromDto(dto: Post) =
            PostEntity(
                idSaved = dto.idSaved,
                idPost = dto.idPost,
                source = dto.source,
                author = dto.author,
                title = dto.title,
                description = dto.description,
                content = dto.content,
                url = dto.url,
                urlToImage = dto.urlToImage,
                publishedAt = dto.publishedAt,
                isLiked = dto.isLiked
            )
    }
}


fun List<PostEntity>.toDto(): List<Post> = map(PostEntity::toDto)
fun List<Post>.toEntity(): List<PostEntity> = map(PostEntity::fromDto)
