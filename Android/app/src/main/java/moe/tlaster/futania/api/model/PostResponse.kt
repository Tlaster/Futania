package moe.tlaster.futania.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PostResponse (
    val posts: List<Post>,
    @SerialName("last_page")
    val lastPage: Long
)

@Serializable
data class Post (
    val id: Long,
    val title: String,
    val comment: String? = null,
    val tags: List<Tag>,
    val rating: String,
    val thumb: Thumb? = null,

    @SerialName("thumb_micro")
    val thumbMicro: String? = null,

    @SerialName("show_adult_thumb")
    val showAdultThumb: Boolean,

    @SerialName("posted_at")
    val postedAt: String,

    @SerialName("likes_count")
    val likesCount: Long,

    val liked: Boolean? = null,

    @SerialName("is_contributor")
    val isContributor: Boolean,

    val uri: PostURI,

    @SerialName("is_pulish_open")
    val isPulishOpen: Boolean,

    @SerialName("is_blog")
    val isBlog: Boolean,

    val fanclub: Fanclub? = null
)


@Serializable
data class PostURI (
    val show: String
)
