package moe.tlaster.futania.api.model

import kotlinx.serialization.Serializable


@Serializable
data class CategoryResponse(
    val categories: List<Category>
)

@Serializable
data class Category(
    val id: Long,
    val name: String,
    val slug: String,
    val uri: URI
)

@Serializable
data class URI(
    val fanclub: String,
    val products: String,
    val posts: String
)
