package com.composeapp.responsivenewsapp.domain.repo

import androidx.paging.PagingData
import com.composeapp.responsivenewsapp.data.models.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepo {
    suspend fun fetchNews(page: Int=1, pageCount: Int=10): Flow<PagingData<Article>>
}