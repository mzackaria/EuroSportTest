package com.mdroid.eurosporttest.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mdroid.eurosporttest.R
import com.mdroid.eurosporttest.local.data.News
import com.mdroid.eurosporttest.ui.ImageAndCapsule
import com.mdroid.eurosporttest.ui.Screen
import com.mdroid.eurosporttest.ui.util.Title
import com.mdroid.eurosporttest.util.formatLabelNumberViews
import com.mdroid.eurosporttest.util.formatLabelStories
import com.mdroid.eurosporttest.util.sinceFormat


@Composable
fun NewsCard(
    imageUrl: String,
    category: String,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    isVideo: Boolean
) {

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = 8.dp
    ) {
        Column {
            ImageAndCapsule(imageUrl, category, isVideo)
            Title(
                title = title.uppercase(),
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
            )
            Text(
                text = subtitle,
                fontSize = 12.sp,
                color = Color.LightGray,
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun VideoCard(
    navController: NavHostController,
    video: News.Video
) {

    NewsCard(
        imageUrl = video.thumb.toString(),
        category = video.sport?.name.toString(),
        title = video.title.toString(),
        subtitle = formatLabelNumberViews(video.views ?: 0),
        onClick = {
            navController.navigate(
                Screen.VideoScreen.withArgs(
                    url = video.url.toString()
                )
            )},
        isVideo = true
    )
}

@Composable
fun StoryCard(
    navController: NavHostController,
    story: News.Story
) {

    //TODO pas tres beau
    val since = sinceFormat(story.date ?: 0.0)

    NewsCard(
        imageUrl = story.image.toString(),
        category = story.sport?.name.toString(),
        title = story.title.toString(),
        subtitle = formatLabelStories(story.author.toString(), story.date ?: 0.0),
        onClick = {
            navController.navigate(
                Screen.StoryScreen.withArgs(
                    title = story.title.toString(),
                    author = story.author.toString(),
                    urlImage = story.image.toString(),
                    teaser = story.teaser.toString(),
                    category = story.sport?.name.toString(),
                    since = since
                )
            )},
        isVideo = false
    )
}

@Preview
@Composable
fun NewsCardPreview() {
    NewsCard(
        imageUrl = "https://i.eurosport.com/2020/01/27/2763745-57096910-2560-1440.jpg",
        category = "football",
        title = "CHRONIQUE FRITSCH",
        subtitle = stringResource(R.string.format_label_views).format(1201),
        onClick = {},
        isVideo = false
    )
}