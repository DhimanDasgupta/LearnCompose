package com.dhimandasgupta.common.news.remote

import com.dhimandasgupta.common.news.remote.entity.NewsResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging

interface NewsApiService {
    companion object {
        fun create(): NewsApiService = NewsApiServiceImpl(
            client = HttpClient(Android) {
                install(Logging) {
                    level = LogLevel.ALL
                }
                install(JsonFeature) {
                    serializer = KotlinxSerializer()
                }
            }
        )
    }

    suspend fun getNews(
        query: String
    ): Result<NewsResponse>
}