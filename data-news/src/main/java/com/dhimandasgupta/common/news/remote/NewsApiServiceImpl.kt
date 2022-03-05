package com.dhimandasgupta.common.news.remote

import com.dhimandasgupta.common.news.remote.entity.NewsResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class NewsApiServiceImpl(
    private val client: HttpClient
) : NewsApiService {

    override suspend fun getNews(
        query: String
    ): Result<NewsResponse> = kotlin.runCatching {
        client.get {
            url(HttpRoutes.NEWS)
            parameter("q", query)
            /**
             * // from - yyyy-MM-dd
             * For free Account using this may return empty article list
             * so get a day prior than current date may be more appropriate
             * */
            parameter("from", getCurrentDate())
            parameter("sortBy", "publishedAt")
            /**
             * If apiKey is passed as parameter
             * */
            //parameter("apiKey", "add your key here")
            /**
             * If apiKey is passed as header
             * */
            header("x-api-key", "add your key here")
        }
    }
}

/**
 * Instant to formatted date
 *
 * Not aware of any DateFormat in kotlinx.datetime as of now
 * */
private fun getCurrentDate(): String {
    val instant = Clock.System.now()
    val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
    return "${dateTime.date.year}-${dateTime.date.monthNumber}-${dateTime.date.dayOfMonth}"
}