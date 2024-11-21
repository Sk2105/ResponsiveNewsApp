package com.composeapp.responsivenewsapp.domain.states

import com.composeapp.responsivenewsapp.data.models.NewsResponse


sealed class NewsState {
    data object Loading : NewsState()
    data class Success(val news: NewsResponse) : NewsState()
    data class Error(val message: String) : NewsState()

}