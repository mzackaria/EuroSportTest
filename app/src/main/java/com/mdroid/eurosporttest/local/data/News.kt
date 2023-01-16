package com.mdroid.eurosporttest.local.data

open class News() {
    class Video(
        val date: Double?,
        val sport: Sport?,
        val thumb: String?,
        val title: String?,
        val url: String?,
        val views: Int?
    ) : News()

    class Story(
        val author: String?,
        val date: Double?,
        val image: String?,
        val sport: Sport?,
        val teaser: String?,
        val title: String?
    ) : News()
}
