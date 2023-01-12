package com.mdroid.eurosporttest.remote.data


import com.mdroid.eurosporttest.local.data.News
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoRemote(
    @Json(name = "date")
    val date: Double?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "sport")
    val sport: SportRemote?,
    @Json(name = "thumb")
    val thumb: String?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "views")
    val views: Int?
) {
    
    fun toLocalModel(): News.Video {
        return News.Video(
            date = date,
            id = id,
            sport = sport,
            thumb = thumb,
            title = title,
            url = url,
            views = views,
        )
    }
}