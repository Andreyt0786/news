package ru.aston.news.entity.headline


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.aston.news.dto.Post
import ru.aston.news.dto.Source

@Entity
data class GeneralEntity(
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

    ) {

    fun toDto() = Post(
        idPost = idPost.hashCode(),
        source = source,
        author = author,
        title = title,
        description = description,
        content = content,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
    )

    companion object {
        fun fromDto(dto: Post) =
            GeneralEntity(
                idPost = dto.idPost,
                source = dto.source,
                author = dto.author,
                title = dto.title,
                description = dto.description,
                content = dto.content,
                url = dto.url,
                urlToImage = dto.urlToImage,
                publishedAt = dto.publishedAt,
            )

    }

}

fun List<GeneralEntity>.toDto(): List<Post> = map(GeneralEntity::toDto)
fun List<Post>.toGeneralEntity(): List<GeneralEntity> = map(GeneralEntity.Companion::fromDto)
