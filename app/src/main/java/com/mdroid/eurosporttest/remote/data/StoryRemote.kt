package com.mdroid.eurosporttest.remote.data


import com.mdroid.eurosporttest.local.data.News
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StoryRemote(
    @Json(name = "author")
    val author: String?,
    @Json(name = "date")
    val date: Double?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "image")
    val image: String?,
    @Json(name = "sport")
    val sport: SportRemote?,
    @Json(name = "teaser")
    val teaser: String?,
    @Json(name = "title")
    val title: String?
) {
    
    fun toLocalModel(): News.Story {
        return News.Story(
            author =  author,
            date =  date,
            id =  id,
            image =  image,
            sport =  sport,
            teaser =  teaser,
            title =  title,
        )
    }
}