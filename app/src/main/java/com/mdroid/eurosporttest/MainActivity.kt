package com.mdroid.eurosporttest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mdroid.eurosporttest.local.data.News
import com.mdroid.eurosporttest.ui.Screen
import com.mdroid.eurosporttest.ui.main.MainScreen
import com.mdroid.eurosporttest.ui.main.MainViewModel
import com.mdroid.eurosporttest.ui.theme.EurosSportTestTheme
import com.mdroid.eurosporttest.util.Response
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EurosSportTestTheme {
                val viewModel = hiltViewModel<MainViewModel>()
                Navigation(viewModel.news)
            }
        }
    }
}


@Composable
fun Navigation(listofNews: State<Response<List<News>>>) {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController, listofNews)
        }
    }

}