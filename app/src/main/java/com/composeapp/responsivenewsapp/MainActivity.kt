package com.composeapp.responsivenewsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import com.composeapp.responsivenewsapp.presentation.home.HomeScreen
import com.composeapp.responsivenewsapp.ui.theme.ResponsiveNewsAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ResponsiveNewsAppTheme {
                val viewModel: MainViewModel = hiltViewModel()


                var currentRoute by remember {
                    mutableStateOf(Screen.Home)
                }


                NavigationSuiteScaffold(modifier = Modifier.fillMaxSize(), navigationSuiteItems = {
                    Screen.entries.forEach {
                        item(
                            icon = {
                                Icon(imageVector = it.icon, contentDescription = "")
                            },
                            label = {
                                Text(text = it.name)
                            },
                            onClick = {
                                currentRoute = it
                            },
                            selected = currentRoute == it
                        )

                    }
                }) {
                    when (currentRoute) {
                        Screen.Home -> {
                            HomeScreen(viewModel = viewModel)

                        }

                        Screen.Search -> {
                            Text(text = "Search")
                        }

                        Screen.Settings -> {
                            Text(text = "Settings")
                        }

                        Screen.Profile -> {
                            Text(text = "Profile")
                        }
                    }
                }

            }
        }
    }
}


enum class Screen(
    val icon: ImageVector
) {
    Home(Icons.Rounded.Home),
    Search(Icons.Rounded.Search),
    Settings(Icons.Rounded.Settings),
    Profile(Icons.Rounded.AccountCircle)
}