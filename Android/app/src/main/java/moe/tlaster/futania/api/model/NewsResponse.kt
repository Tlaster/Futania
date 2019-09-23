package moe.tlaster.futania.api.model

import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse (
    val news: Map<String, NewsData>,
    val created: String
)

@Serializable
data class NewsData (
    val date: String,
    val title: String,
    val url: String,
    val userTypes: List<UserType>
)

enum class UserType {
    Creator,
    Fan,
    Visitor
}
