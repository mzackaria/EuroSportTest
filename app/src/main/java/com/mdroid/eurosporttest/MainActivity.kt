package com.mdroid.eurosporttest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mdroid.eurosporttest.local.data.News
import com.mdroid.eurosporttest.ui.Screen
import com.mdroid.eurosporttest.ui.main.MainScreen
import com.mdroid.eurosporttest.ui.main.MainViewModel
import com.mdroid.eurosporttest.ui.story.StoryScreen
import com.mdroid.eurosporttest.ui.theme.EurosSportTestTheme
import com.mdroid.eurosporttest.ui.video.VideoScreen
import com.mdroid.eurosporttest.util.Response
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EurosSportTestTheme {
                val viewModel = hiltViewModel<MainViewModel>()
                Navigation(
                    viewModel.news
                )
            }
        }
    }
}

@Composable
fun Navigation(
    listOfNews: State<Response<List<News>>>
) {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController, listOfNews)
        }
        composable(
            route = Screen.StoryScreen.getFinalRoute()
        ) {entry ->
            StoryScreen(
                navController,
                title = entry.arguments?.getString("title"),
                author = entry.arguments?.getString("author"),
                urlImage = entry.arguments?.getString("urlImage"),
                teaser = entry.arguments?.getString("teaser"),
                category = entry.arguments?.getString("category"),
                since = entry.arguments?.getString("since")
            )
        }
        composable(route = Screen.VideoScreen.getFinalRoute()) { entry ->
            VideoScreen(
                url = entry.arguments?.getString("url").toString()
            )
        }
    }

}