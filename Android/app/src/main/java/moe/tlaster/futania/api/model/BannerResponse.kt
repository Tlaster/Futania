package moe.tlaster.futania.api.model

import kotlinx.serialization.Serializable


@Serializable
data class BannerResponse (
    val bnrs: Map<String, BannerData>,
    val created: String
)

@Serializable
data class BannerData (
    val title: String,
    val image: Image,
    val url: String,
    val userTypes: List<UserType>
)

@Serializable
data class Image (
    val sm: String,
    val md: String,
    val lg: String
)
