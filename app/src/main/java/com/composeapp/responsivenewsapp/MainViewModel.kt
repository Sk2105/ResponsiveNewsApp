package com.composeapp.responsivenewsapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.composeapp.responsivenewsapp.data.models.Article
import com.composeapp.responsivenewsapp.domain.repo.NewsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val newsRepo: NewsRepo
) : ViewModel() {

    private var _news = MutableStateFlow<PagingData<Article>>(PagingData.empty())

    var news = _news

    init {
        viewModelScope.launch {
            newsRepo.fetchNews().cachedIn(viewModelScope).collect {
                _news.value = it
            }
        }
    }

}