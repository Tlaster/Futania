package moe.tlaster.futania.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ProductResponse (
    val products: List<Product>,

    @SerialName("last_page")
    val lastPage: Long
)

@Serializable
data class Product (
    val id: Long,
    val name: String,
    val type: String,
    val category: ProductCategory,
    val thumb: Thumb? = null,

    @SerialName("show_adult_thumb")
    val showAdultThumb: Boolean,

    val stock: String,
    val price: Long,

    @SerialName("buyable_lowest_plan")
    val buyableLowestPlan: BuyableLowestPlan,

    val likes: Likes,
    val uri: String,
    val reactions: Reactions,
    val fanclub: Fanclub
)

@Serializable
data class BuyableLowestPlan (
    val id: Long,
    val price: Long,
    val name: String,
    val description: String,
    val limit: Long,
    val thumb: String
)

@Serializable
data class ProductCategory (
    val id: Long,
    val name: String,
    val uri: String
)


@Serializable
data class Likes (
    val count: Long,

    @SerialName("has_like")
    val hasLike: Boolean
)

@Serializable
data class Reactions (
    @SerialName("get_url")
    val getURL: String,

    @SerialName("post_uri")
    val postURI: String? = null,

    @SerialName("delete_uri")
    val deleteURI: String
)
