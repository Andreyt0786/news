package ru.aston.news.entity.source


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.aston.news.dto.Post
import ru.aston.news.dto.Source


@Entity
data class CheckSourceEntity(
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
    val isLiked: Boolean
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
            CheckSourceEntity(
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

fun List<CheckSourceEntity>.toDto(): List<Post> = map(CheckSourceEntity::toDto)
fun List<Post>.toCheckSourceEntity(): List<CheckSourceEntity> =
    map(CheckSourceEntity.Companion::fromDto)
