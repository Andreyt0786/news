package ru.aston.news.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.aston.news.dto.Source
import ru.aston.news.dto.SourcePost


@Entity
data class SourcePostEntity(
    @PrimaryKey(autoGenerate = true)
    val idPost: Int,
    val id: String,
    val name: String,
    val description: String,
    val url: String,
    val category: String,
    val language: String,
    val country: String?,

    ) {

    fun toDto() = SourcePost(
        idPost = idPost.hashCode(),
        id = id,
        name = name,
        description = description,
        url = url,
        category = category,
        language = language,
        country = country
    )

    companion object {
        fun fromDto(dto: SourcePost) =
            SourcePostEntity(
                idPost = dto.idPost.hashCode(),
                id = dto.id,
                name = dto.name,
                description = dto.description,
                url = dto.url,
                category = dto.category,
                language = dto.language,
                country = dto.country
            )

    }

}

fun List<SourcePostEntity>.toDto(): List<SourcePost> = map(SourcePostEntity::toDto)
fun List<SourcePost>.toSourcePostEntity(): List<SourcePostEntity> = map(SourcePostEntity::fromDto)
