package moe.tlaster.futania.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MeResponse (
    @SerialName("current_user")
    val currentUser: CurrentUser? = null,
    @SerialName("redirect_to")
    val redirectTo: String? = null
)

@Serializable
data class CurrentUser (
    val id: Long,

    @SerialName("toranoana_identify_token")
    val toranoanaIdentifyToken: String,

    val name: String,
    val image: Image,

    @SerialName("profile_text")
    val profileText: String? = null,

    @SerialName("has_fanclub")
    val hasFanclub: Boolean? = null,

    val fanclub: Fanclub? = null,

    @SerialName("show_adult")
    val showAdult: Boolean,

    @SerialName("joined_fanclubs_count")
    val joinedFanclubsCount: Long
)
