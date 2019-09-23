package moe.tlaster.futania.api

import com.github.kittinunf.fuel.core.*
import com.github.kittinunf.fuel.coroutines.awaitObject
import com.github.kittinunf.fuel.coroutines.awaitObjectResponse
import com.github.kittinunf.fuel.coroutines.awaitObjectResponseResult
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.serialization.kotlinxDeserializerOf
import com.github.kittinunf.fuel.serialization.responseObject
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import moe.tlaster.futania.api.model.BannerResponse
import moe.tlaster.futania.api.model.NewsResponse


object Api {
    suspend fun news(): NewsResponse {
        return "https://spotlight.fantia.jp/wp-json/api/v1/news"
            .httpGet()
            .awaitObject(kotlinxDeserializerOf(NewsResponse.serializer(), json = Json.nonstrict))
    }

    suspend fun banners(): BannerResponse {
        return "https://spotlight.fantia.jp/wp-json/api/v1/banners"
            .httpGet()
            .awaitObject(kotlinxDeserializerOf(BannerResponse.serializer(), json = Json.nonstrict))
    }
}