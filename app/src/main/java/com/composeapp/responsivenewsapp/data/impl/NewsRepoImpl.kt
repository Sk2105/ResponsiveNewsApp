package com.composeapp.responsivenewsapp.data.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.composeapp.responsivenewsapp.data.datasource.NewsDataSource
import com.composeapp.responsivenewsapp.data.models.Article
import com.composeapp.responsivenewsapp.data.remote.NewsSource
import com.composeapp.responsivenewsapp.domain.repo.NewsRepo
import com.composeapp.responsivenewsapp.domain.usecases.FetchNewsUseCases
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepoImpl @Inject constructor(
    private val newsSource: NewsSource
) :NewsRepo{
    override suspend fun fetchNews(page: Int , pageCount: Int): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10
            ),
            initialKey = 1,
            pagingSourceFactory = { NewsDataSource(newsSource) }
        ).flow
    }
}