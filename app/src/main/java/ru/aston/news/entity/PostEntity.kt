package ru.aston.news.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.aston.news.dto.Post
import ru.aston.news.dto.Source

@Entity
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val idPost: Long,
    @Embedded
    val source : Source,
    val author: String?,
    val title: String,
    val desription:String?,
    val url: String,
    val urlToImage:String?,
    val publishedAt:String,
    val content:String?,

) {

    fun toDto() = Post(
        idPost=idPost,
        source = source,
        author = author,
        title = title,
        desription = desription,
        content = content,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
    )

    companion object {
        fun fromDto(dto: Post) =
            PostEntity(
                idPost=dto.idPost,
                source = dto.source,
                author = dto.author,
                title = dto.title,
                desription = dto.desription,
                content = dto.content,
                url = dto.url,
                urlToImage = dto.urlToImage,
                publishedAt = dto.publishedAt,
            )

    }

}

fun List<PostEntity>.toDto(): List<Post> = map(PostEntity::toDto)
fun List<Post>.toEntity(): List<PostEntity> = map(PostEntity::fromDto)
