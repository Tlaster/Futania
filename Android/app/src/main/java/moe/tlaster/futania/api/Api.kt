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

    suspend fun banners(type: BannerType = BannerType.primary, brand: SiteType = SiteType.general): BannerResponse {
        return "https://spotlight.$HOST/wp-json/api/v1/banners"
            .httpGet(
                listOf(
                    "type" to type.name,
                    "brand" to brand.name
                )
            )
            .awaitObject(BannerResponse.serializer())
    }

    suspend fun category(): CategoryResponse {
        return "https://$HOST/api/v1/fanclubs/categories"
            .httpGet()
            .awaitObject(CategoryResponse.serializer())
    }

    suspend fun me(): MeResponse {
        return "https://$HOST/api/v1/me"
            .httpGet()
            .awaitObject(MeResponse.serializer())
    }

    object Timeline {
        suspend fun posts(
            page: Int,
            per: Int
        ): PostResponse {
            return "https://$HOST/api/v1/me/timelines/posts"
                .httpGet(
                    listOf(
                        "page" to page,
                        "per" to per
                    )
                )
                .awaitObject(PostResponse.serializer())
        }

        suspend fun products(
            page: Int,
            per: Int
        ): ProductResponse {
            return "https://$HOST/api/v1/me/timelines/products"
                .httpGet(
                    listOf(
                        "page" to page,
                        "per" to per
                    )
                )
                .awaitObject(ProductResponse.serializer())
        }
    }

    object Ranking {
        suspend fun fanclubs(
            category: String,
            period: PeriodType,
            kind: RankingType,
            page: Int = 1,
            per_page: Int = 12,
            gender_type: SiteType = SiteType.general
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
            period: PeriodType,
            kind: RankingType,
            page: Int = 1,
            per_page: Int = 12,
            gender_type: SiteType = SiteType.general
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

        suspend fun products(
            category: String,
            period: PeriodType,
            kind: RankingType,
            page: Int = 1,
            per_page: Int = 12,
            gender_type: SiteType = SiteType.general
        ): ProductResponse {
            return "https://$HOST/api/v1/ranking/products"
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
                .awaitObject(ProductResponse.serializer())
        }
    }

    object Search {

        suspend fun fanclubs(
            category: String,
            order: OrderType,
            page: Int = 1,
            per_page: Int = 12,
            siteType: SiteType = SiteType.general
        ): FanclubResponse {
            return "https://$HOST/api/v1/search/fanclubs"
                .httpGet(
                    listOf(
                        "order" to order.name,
                        "category" to category,
                        "page" to page,
                        "per_page" to per_page,
                        "princess" to siteType.ordinal
                    )
                )
                .awaitObject(FanclubResponse.serializer())
        }

        suspend fun posts(
            adult: R18Type,
            category: String,
            order: OrderType,
            page: Int = 1,
            per_page: Int = 12,
            siteType: SiteType = SiteType.general
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
                        when {
                            adult != R18Type.both -> it + ("adult" to when (adult) {
                                R18Type.non -> 0
                                R18Type.only -> 1
                                else -> throw Error()
                            })
                            else -> it
                        }
                    }
                )
                .awaitObject(PostResponse.serializer())
        }
    }


    suspend fun products(
        adult: R18Type,
        category: String,
        in_stock: Boolean,
        order: OrderType,
        product_type: ProductType,
        page: Int = 1,
        per_page: Int = 12,
        siteType: SiteType = SiteType.general
    ): ProductResponse {
        return "https://$HOST/api/v1/search/products"
            .httpGet(
                listOf(
                    "order" to order.name,
                    "category" to category,
                    "page" to page,
                    "per_page" to per_page,
                    "princess" to siteType.ordinal
                ).let {
                    when {
                        adult != R18Type.both -> it + ("adult" to when (adult) {
                            R18Type.non -> 0
                            R18Type.only -> 1
                            else -> throw Error()
                        })
                        else -> it
                    }
                }.let {
                    when {
                        in_stock -> it + ("in_stock" to 1)
                        else -> it
                    }
                }.let {
                    when {
                        product_type != ProductType.all -> it + ("product_type" to when (product_type) {
                            ProductType.transfer_by_self -> 0
                            ProductType.download -> 1
                            else -> throw Error()
                        })
                        else -> it
                    }
                }
            )
            .awaitObject(ProductResponse.serializer())
    }
}