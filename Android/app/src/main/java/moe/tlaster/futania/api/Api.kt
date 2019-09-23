package moe.tlaster.futania.api

import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.coroutines.awaitObject
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.serialization.kotlinxDeserializerOf
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.Json
import moe.tlaster.futania.api.model.*

inline fun <reified T : Any> defaultKotlinxDeserializerOf(loader: DeserializationStrategy<T>): ResponseDeserializable<T> {
    return kotlinxDeserializerOf(loader, json = Json.nonstrict)
}

suspend inline fun <reified T : Any> Request.awaitObject(loader: DeserializationStrategy<T>): T {
    return this.awaitObject(defaultKotlinxDeserializerOf(loader))
}

object Api {
    private val HOST = "fantia.jp"

    suspend fun news(): NewsResponse {
        return "https://spotlight.$HOST/wp-json/api/v1/news"
            .httpGet()
            .awaitObject(NewsResponse.serializer())
    }

    suspend fun banners(type: String = "primary", brand: String = "general"): BannerResponse {
        return "https://spotlight.$HOST/wp-json/api/v1/banners"
            .httpGet(
                listOf(
                    "type" to type,
                    "brand" to brand
                )
            )
            .awaitObject(BannerResponse.serializer())
    }

    suspend fun catrgory(): CategoryResponse {
        return "https://$HOST/api/v1/fanclubs/categories"
            .httpGet()
            .awaitObject(CategoryResponse.serializer())
    }

    object Ranking {
        suspend fun fanclubs(
            category: String,
            gender_type: SiteType,
            kind: RankingType,
            page: Int,
            per_page: Int,
            period: PeriodType
        ): FanclubResponse {
            return "https://$HOST/api/v1/ranking/fanclubs"
                .httpGet(
                    listOf(
                        "category" to category,
                        "gender_type" to gender_type.name,
                        "kind" to kind.name,
                        "page" to page,
                        "per_page" to per_page,
                        "period" to period.name
                    )
                )
                .awaitObject(FanclubResponse.serializer())
        }

        suspend fun posts(
            category: String,
            gender_type: SiteType,
            kind: RankingType,
            page: Int,
            per_page: Int,
            period: PeriodType
        ): PostResponse {
            return "https://$HOST/api/v1/ranking/posts"
                .httpGet(
                    listOf(
                        "category" to category,
                        "gender_type" to gender_type.name,
                        "kind" to kind.name,
                        "page" to page,
                        "per_page" to per_page,
                        "period" to period.name
                    )
                )
                .awaitObject(PostResponse.serializer())
        }
    }

    object Search {
        suspend fun posts(
            adult: R18Type,
            category: String,
            order: OrderType,
            page: Int,
            per_page: Int,
            siteType: SiteType
        ): PostResponse {
            return "https://$HOST/api/v1/search/posts"
                .httpGet(
                    listOf(
                        "order" to order.name,
                        "category" to category,
                        "page" to page,
                        "per_page" to per_page,
                        "princess" to siteType.ordinal
                    ).let {
                        if (adult != R18Type.both) {
                            it + ("adult" to if (adult == R18Type.non) {
                                0
                            } else {
                                1
                            })
                        }
                        it
                    }
                )
                .awaitObject(PostResponse.serializer())
        }
    }
}