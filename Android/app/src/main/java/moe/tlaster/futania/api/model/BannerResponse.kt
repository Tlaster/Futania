package moe.tlaster.futania.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class BannerResponse(
    val bnrs: Map<String, BannerData>,
    val created: String
)

@Serializable
data class BannerData(
    val title: String,
    val image: BannerImage,
    val url: String,
    @SerialName("user_types")
    val userTypes: List<String>
)

@Serializable
data class BannerImage(
    val sm: String,
    val md: String,
    val lg: String
)
