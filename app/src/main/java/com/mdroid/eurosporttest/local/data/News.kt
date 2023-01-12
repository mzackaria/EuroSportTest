package com.mdroid.eurosporttest.local.data

import com.mdroid.eurosporttest.remote.data.SportRemote

open class News() {
    class Video(
        val date: Double?,
        val id: Int?,
        val sport: SportRemote?,
        val thumb: String?,
        val title: String?,
        val url: String?,
        val views: Int?
    ) : News()

    class Story(
        val author: String?,
        val date: Double?,
        val id: Int?,
        val image: String?,
        val sport: SportRemote?,
        val teaser: String?,
        val title: String?
    ) : News()
}
