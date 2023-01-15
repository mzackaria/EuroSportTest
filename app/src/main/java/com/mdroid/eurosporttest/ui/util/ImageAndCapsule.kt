package com.mdroid.eurosporttest.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.mdroid.eurosporttest.R

@Composable
fun ImageAndCapsule(
    imageUrl: String,
    category: String,
    isVideo: Boolean = false
) {
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
}