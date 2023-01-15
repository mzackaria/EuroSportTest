package com.mdroid.eurosporttest.ui.util

import android.content.Context
import android.content.Intent
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.mdroid.eurosporttest.R


@Composable
fun BackButton(navHostController: NavHostController) {
    IconButton(
        onClick = {navHostController.navigateUp()}
    ) {
        Icon(
            painter = painterResource(R.drawable.back),
            contentDescription = stringResource(id = R.string.description_back_button),
            tint = Color.White
        )
    }
}

@Composable
fun ShareButton(title: String?, context: Context) {
    IconButton(
        onClick = { sharePost(title.toString(), context) }
    ) {
        Icon(
            painter = painterResource(R.drawable.share),
            contentDescription = stringResource(id = R.string.share),
            tint = Color.White
        )
    }
}

/**
 * @param title of te news to share
 * @param context Android context to show the share sheet in
 */
fun sharePost(
    title: String,
    context: Context
) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TITLE, title)
    }
    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.share)
        )
    )
}
