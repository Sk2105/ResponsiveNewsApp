package com.composeapp.responsivenewsapp.data.remote

import com.composeapp.responsivenewsapp.data.models.NewsResponse
import com.composeapp.responsivenewsapp.utils.API_KEY
import com.composeapp.responsivenewsapp.utils.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject


class NewsSource @Inject constructor(private val httpClient: HttpClient) {


    suspend fun fetchNews(
        q: String = "everything",
        apikey: String = API_KEY,
        page: Int = 1,
        pageCount: Int = 10
    ) = httpClient.get(
        urlString = "$BASE_URL?apikey=$apikey&page=$page&pageCount=$pageCount&q=$q",
    ).body<NewsResponse>()
}