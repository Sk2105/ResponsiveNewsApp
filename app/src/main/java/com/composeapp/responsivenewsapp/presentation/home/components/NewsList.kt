package com.composeapp.responsivenewsapp.presentation.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.composeapp.responsivenewsapp.MainViewModel
import com.composeapp.responsivenewsapp.data.models.Article


@Composable
fun NewsList(viewModel: MainViewModel, onClick: (Article) -> Unit) {

    val news = viewModel.news.collectAsLazyPagingItems()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        when (news.loadState.refresh) {
            is LoadState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Loading...")
                }
            }

            is LoadState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Error Occurred ${(news.loadState.refresh as LoadState.Error).error.message}")
                }
            }

            is LoadState.NotLoading -> {

                news.itemSnapshotList.forEach { article ->
                    NewsItem(article = article!!, onClick = onClick)
                }
            }
        }


    }

}

@Composable
fun NewsItem(article: Article, onClick: (Article) -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 8.dp),
        onClick = { onClick(article) }


    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = article.urlToImage ?: "",
                contentDescription = "",
                modifier = Modifier
                    .size(150.dp)
                    .clip(
                        RoundedCornerShape(16.dp)
                    ),
                contentScale = ContentScale.FillBounds
            )

            Column(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    text = article.title ?: "",
                    maxLines = 2,
                    modifier = Modifier.padding(vertical = 4.dp),
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))
                // published by
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.AccountCircle,
                        contentDescription = "",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = article.source?.name ?: "",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // author
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.List,
                        contentDescription = "",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = article.author ?: "",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }


                Spacer(modifier = Modifier.height(8.dp))
                // published at
                Row {
                    Icon(
                        imageVector = Icons.Rounded.FavoriteBorder,
                        contentDescription = "",
                        modifier = Modifier.size(24.dp)

                    )
                    Spacer(modifier = Modifier.padding(4.dp))

                    Icon(
                        imageVector = Icons.Rounded.Share,
                        contentDescription = "",
                        modifier = Modifier.size(24.dp)
                    )

                }

            }


        }

    }
}