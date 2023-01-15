package com.mdroid.eurosporttest.ui.util

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun Title(
    title: String,
    modifier: Modifier =  Modifier
) {
    Text(
        text = title,
        fontSize = 20.sp,
        modifier = modifier,
        color = MaterialTheme.colors.primary,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.SansSerif
    )
}