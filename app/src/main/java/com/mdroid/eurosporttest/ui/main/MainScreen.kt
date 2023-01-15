package com.mdroid.eurosporttest.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mdroid.eurosporttest.R
import com.mdroid.eurosporttest.local.data.News
import com.mdroid.eurosporttest.util.Response

@Composable
fun MainScreen(
    navController: NavHostController,
    stateListOfNews: State<Response<List<News>>>
) {
    val responseListOfNews = stateListOfNews.value
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                            Text(
                                stringResource(R.string.app_bar_label).uppercase(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                backgroundColor = MaterialTheme.colors.primary,
                modifier = Modifier.fillMaxWidth()
            )
                 },

        content = {
            when(responseListOfNews) {
                is Response.Success -> NewsList(navController, responseListOfNews.data ?: emptyList())
            }

        }
    )
}

@Composable
fun NewsList(
    navController: NavHostController,
    listOfNews: List<News>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(listOfNews) { news ->
            when(news) {
                is News.Video -> VideoCard(navController, news)
                is News.Story -> StoryCard(navController, news)
            }
        }
    }
    
}
