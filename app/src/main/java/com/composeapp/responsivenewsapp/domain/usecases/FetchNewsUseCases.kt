package com.composeapp.responsivenewsapp.domain.usecases

import androidx.paging.PagingData
import com.composeapp.responsivenewsapp.data.models.Article
import com.composeapp.responsivenewsapp.domain.repo.NewsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class FetchNewsUseCases @Inject constructor(private val newsRepo: NewsRepo) {

    suspend fun invoke(
        page: Int = 1,
        pageCount: Int = 10
    ): Flow<PagingData<Article>>
    {
        val news = newsRepo.fetchNews(page,pageCount)

        return news
    }

}