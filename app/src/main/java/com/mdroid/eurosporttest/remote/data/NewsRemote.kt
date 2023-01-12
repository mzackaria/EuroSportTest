package com.mdroid.eurosporttest.remote.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsRemote(
    @Json(name = "stories")
    val stories: List<StoryRemote>?,
    @Json(name = "videos")
    val videos: List<VideoRemote>?
)