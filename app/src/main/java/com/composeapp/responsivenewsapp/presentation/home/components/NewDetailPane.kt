package com.composeapp.responsivenewsapp.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.composeapp.responsivenewsapp.data.models.Article


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailPane(
    article: Article,
    onBackClick: () -> Unit
) {


    Scaffold(
        topBar = {
            TopAppBar(title = { }, navigationIcon = {
                IconButton(onClick = {onBackClick() }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = ""
                    )
                }
            })

        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState())
            ,

            verticalArrangement = Arrangement.Top
        ) {


            Text(
                text = article.title?:"",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)

            )

            Spacer(modifier = Modifier.padding(8.dp))
            AsyncImage(
                model = article.urlToImage?:"",
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Fit

            )

            Spacer(modifier = Modifier.padding(8.dp))

            Row(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {

                Text(
                    text = article.source?.name?:"",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )

                Text(
                    text = article.publishedAt?:"",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }


            Spacer(modifier = Modifier.padding(8.dp))

            Text(
                text = article.content?:"",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp)
            )

            Spacer(modifier = Modifier.padding(8.dp))

            Text(
                text = article.description?:"",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )


            Spacer(modifier = Modifier.padding(8.dp))

            Text(
                text = article.author?:"",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(16.dp)
            )





        }
    }

}