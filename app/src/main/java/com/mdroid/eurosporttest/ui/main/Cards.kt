package com.mdroid.eurosporttest.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mdroid.eurosporttest.R
import com.mdroid.eurosporttest.local.data.News
import com.mdroid.eurosporttest.util.formatLabelNumberViews
import com.mdroid.eurosporttest.util.formatLabelStories


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
            .fillMaxWidth(),
        elevation = 8.dp
    ) {
        Column {
            ConstraintLayout {
                val (image, label) = createRefs()
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .constrainAs(image) {}
                ) {
                    AsyncImage(
                        model = imageUrl,
                        contentScale = ContentScale.FillBounds,
                        contentDescription = null,  // decorative
                        modifier = Modifier.align(Alignment.Center).fillMaxSize()
                    )
                    if (isVideo) {
                        Image(
                            painter = painterResource(id = R.drawable.play),
                            contentDescription = null, // decorative
                            contentScale = ContentScale.None,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                }
                Surface(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .constrainAs(label) {
                            top.linkTo(image.bottom)
                            bottom.linkTo(image.bottom)
                        },
                    color = MaterialTheme.colors.primary,
                    elevation = 8.dp,
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = category.uppercase(),
                        color = Color.White,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
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
        onClick = {},
        isVideo = true
    )
}

@Composable
fun StoryCard(
    navController: NavHostController,
    story: News.Story
) {

    NewsCard(
        imageUrl = story.image.toString(),
        category = story.sport?.name.toString(),
        title = story.title.toString(),
        subtitle = formatLabelStories(story.author.toString(), story.date ?: 0.0),
        onClick = {},
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