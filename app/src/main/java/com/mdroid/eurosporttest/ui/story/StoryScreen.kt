package com.mdroid.eurosporttest.ui.story

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mdroid.eurosporttest.R
import com.mdroid.eurosporttest.ui.ImageAndCapsule
import com.mdroid.eurosporttest.ui.util.BackButton
import com.mdroid.eurosporttest.ui.util.ShareButton
import com.mdroid.eurosporttest.ui.util.Title

@Composable
fun StoryScreen(
    navHostController: NavHostController,
    title: String?,
    author: String?,
    urlImage: String?,
    teaser: String?,
    category: String?,
    since: String?
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
            .fillMaxSize()
        ) {

            ImageAndCapsule(
                urlImage.toString(),
                category.toString()
            )

            Column(
                modifier = Modifier.padding(8.dp)
            ) {

                Title(
                    title.toString(),
                )

                //by Author's Name
                Text(
                    text = buildAnnotatedString {
                        append(stringResource(id = R.string.by))
                        append(" ")
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colors.secondary
                            )
                        ) {
                            append(author.toString())
                        }
                    },
                    fontSize = 12.sp
                )

                //12 min ago
                Text(
                    text = since.toString(),
                    fontSize = 10.sp,
                    color = Color.LightGray
                )

                //article
                Text(
                    text = teaser.toString(),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }


        }
        val context = LocalContext.current
        TopAppBar(
            elevation = 0.dp,
            modifier = Modifier.fillMaxWidth(),
            navigationIcon = {
                BackButton(navHostController)
            },
            actions = {
                ShareButton(title, context)
            },
            backgroundColor = Color.Transparent,
            title = {}
        )
    }
}
