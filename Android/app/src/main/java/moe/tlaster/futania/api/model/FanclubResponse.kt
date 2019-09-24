package moe.tlaster.futania.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class FanclubResponse(
    val fanclubs: List<Fanclub>,
    @SerialName("last_page")
    val lastPage: Long
)

@Serializable
data class Fanclub(
    val id: Long,
    val user: User,
    val category: Category,

    @SerialName("fanclub_name_with_creator_name")
    val fanclubNameWithCreatorName: String,

    @SerialName("fanclub_name_or_creator_name")
    val fanclubNameOrCreatorName: String,

    val title: String,
    val cover: Cover,
    val icon: Icon,

    @SerialName("is_join")
    val isJoin: Boolean,

    @SerialName("fan_count")
    val fanCount: Long,

    @SerialName("posts_count")
    val postsCount: Long,

    @SerialName("products_count")
    val productsCount: Long,

    val uri: FanclubURI,

    @SerialName("user_support_point")
    val userSupportPoint: Long,

    @SerialName("recent_posts")
    val recentPosts: List<Post>? = null
)

@Serializable
data class Cover(
    val thumb: String,
    val medium: String,
    val main: String,
    val ogp: String,
    val original: String
)

@Serializable
data class Icon(
    val thumb: String,
    val main: String,
    val original: String
)


@Serializable
data class Tag(
    val name: String,
    val uri: String
)

@Serializable
data class Thumb(
    val thumb: String,
    val medium: String,
    val large: String,
    val main: String,
    val ogp: String,
    val micro: String,
    val original: String
)

@Serializable
data class FanclubURI(
    val show: String,
    val posts: String,
    val plans: String,
    val products: String,
    val users: String
)

@Serializable
data class User(
    val id: Long,

    @SerialName("toranoana_identify_token")
    val toranoanaIdentifyToken: String,

    val name: String,
    val image: Image,

    @SerialName("profile_text")
    val profileText: String? = null,

    @SerialName("has_fanclub")
    val hasFanclub: Boolean
)

@Serializable
data class Image(
    val small: String,
    val medium: String,
    val large: String
)