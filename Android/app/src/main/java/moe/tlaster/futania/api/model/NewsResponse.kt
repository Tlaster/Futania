package moe.tlaster.futania.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    val news: Map<UserType, List<NewsData>>,
    val created: String
)

@Serializable
data class NewsData(
    val date: String,
    val title: String,
    val url: String,
    @SerialName("user_types")
    val userTypes: List<String>
)

