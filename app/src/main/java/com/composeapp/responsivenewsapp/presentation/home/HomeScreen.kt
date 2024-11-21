package com.composeapp.responsivenewsapp.presentation.home

import android.os.Parcelable
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.composeapp.responsivenewsapp.MainViewModel
import com.composeapp.responsivenewsapp.data.models.Article
import com.composeapp.responsivenewsapp.presentation.home.components.NewsDetailPane
import com.composeapp.responsivenewsapp.presentation.home.components.NewsList
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize


@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun HomeScreen(viewModel: MainViewModel) {
    val scope = rememberCoroutineScope()


    val navigation = rememberListDetailPaneScaffoldNavigator<MyData>()
    BackHandler {
        scope.launch {
            if (navigation.canNavigateBack()) {
                navigation.navigateBack()
            }
        }
    }

    ListDetailPaneScaffold(
        directive = navigation.scaffoldDirective,
        value = navigation.scaffoldValue,
        listPane = {

            AnimatedPane {
                NewsList(viewModel = viewModel) {
                    scope.launch {
                        navigation.navigateTo(
                            pane = ThreePaneScaffoldRole.Primary,
                            contentKey = MyData(it)
                        )
                    }

                }
            }


        },
        detailPane = {
            AnimatedPane {
                navigation.currentDestination?.let {
                    val article = it.contentKey?.article

                    if (article != null) {
                        NewsDetailPane(article = article, onBackClick = {
                            scope.launch {
                                if (navigation.canNavigateBack()) {
                                    navigation.navigateBack()
                                }
                            }
                        })

                    } else {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
                        {
                            Text(text = "No Data")
                        }
                    }
                }
            }


        })

}


@Parcelize
data class MyData(var article: Article) : Parcelable