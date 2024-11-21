package com.composeapp.responsivenewsapp.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.composeapp.responsivenewsapp.data.models.Article
import com.composeapp.responsivenewsapp.data.remote.NewsSource


class NewsDataSource(val newsSource: NewsSource):PagingSource<Int,Article>(){
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val position = params.key ?: 1
            val response = newsSource.fetchNews(page = position)
            println(response)
            LoadResult.Page(
                data = response.articles.toMutableList(),
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (response.articles.size < 10) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}