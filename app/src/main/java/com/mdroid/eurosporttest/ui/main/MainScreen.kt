package com.mdroid.eurosporttest.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mdroid.eurosporttest.R
import com.mdroid.eurosporttest.local.data.News
import com.mdroid.eurosporttest.util.Response

@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val responseListOfNews = viewModel.news.value
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
                is Response.Success -> NewsList(
                    navController,
                    responseListOfNews.data ?: emptyList(),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                )
                is Response.Error -> Text(
                    text = stringResource(id = R.string.internet_error),
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center

                )
                is Response.Loading -> Loading(Modifier.fillMaxSize())
            }
        }
    )
}

@Composable
fun Loading(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        LinearProgressIndicator()
    }
}

@Composable
fun NewsList(
    navController: NavHostController,
    listOfNews: List<News>,
    modifier: Modifier = Modifier,
    spacedBy: Dp = 8.dp
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacedBy)
    ) {
        items(listOfNews) { news ->
            when(news) {
                is News.Video -> VideoCard(navController, news)
                is News.Story -> StoryCard(navController, news)
            }
        }
    }
}
