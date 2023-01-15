package com.mdroid.eurosporttest.util

import android.text.format.DateUtils
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mdroid.eurosporttest.R
import java.util.*
import kotlin.time.Duration.Companion.days

@Composable
fun formatLabelNumberViews(numberOfViews: Int): String {
    return stringResource(id = R.string.format_label_views, numberOfViews)
}

@Composable
fun formatLabelStories(author: String, date: Double): String {
    val timeString = sinceFormat(date)
    return stringResource(id = R.string.format_label_card_story, author, timeString)
}

//TODO REFAIRE CE TRUC
@Composable
fun sinceFormat(date: Double): String {

    val currentTimestamp = System.currentTimeMillis()

    val diff: Long = currentTimestamp - (date * 1000.0).toLong()
    val seconds = diff / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24
    val years = days / 365

    if (hours == 0L && days == 0L && years == 0L) {
        val unit = stringResource(id = R.string.minutes)
        return stringResource(id = R.string.format_label_since).format(minutes, unit)
    } else if (days == 0L && years == 0L) {
        val unit = stringResource(id = R.string.hours)
        return stringResource(id = R.string.format_label_since).format(hours, unit)
    } else if (years == 0L) {
        val unit = stringResource(id = R.string.day)
        return stringResource(id = R.string.format_label_since).format(days, unit)
    } else {
        val unit = stringResource(id = R.string.years)
        return stringResource(id = R.string.format_label_since).format(years, unit)
    }
}