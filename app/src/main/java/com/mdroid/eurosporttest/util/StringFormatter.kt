package com.mdroid.eurosporttest.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mdroid.eurosporttest.R
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.DurationUnit

@Composable
fun formatLabelNumberViews(numberOfViews: Int): String {
    return stringResource(id = R.string.format_label_views, numberOfViews)
}

@Composable
fun formatLabelStories(author: String, date: Double): String {
    val timeString = sinceFormat(date)
    return stringResource(id = R.string.format_label_card_story, author, timeString)
}

@Composable
fun sinceFormat(date: Double): String {

    val formatSinceString = stringResource(id = R.string.format_label_since)

    val currentTimestamp = System.currentTimeMillis()
    val diffDuration: Duration = (currentTimestamp - (date * 1000.0).toLong()).milliseconds

    val days = diffDuration.inWholeDays
    val hours = diffDuration.inWholeHours
    val minutes = diffDuration.inWholeMinutes
    val seconds = diffDuration.inWholeSeconds

    return formatSinceString.format(
         when {
            days > 364 -> (days / 365).toString() + stringResource(id = R.string.years)
            hours > 23 -> diffDuration.toString(DurationUnit.DAYS)
            minutes > 59 -> diffDuration.toString(DurationUnit.HOURS)
            seconds > 59 -> diffDuration.toString(DurationUnit.MINUTES)
            else -> stringResource(id = R.string.a_few_seconds_label)
        }
    )
}
