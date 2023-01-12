package com.mdroid.eurosporttest.local.data

import com.mdroid.eurosporttest.remote.data.SportRemote

open class News(val date: Double?) {
    class Video(
        date: Double?,
        id: Int?,
        sport: SportRemote?,
        thumb: String?,
        title: String?,
        url: String?,
        views: Int?
    ) : News(date)

    class Story(
        author: String?,
        date: Double?,
        id: Int?,
        image: String?,
        sport: SportRemote?,
        teaser: String?,
        title: String?
    ) : News(date)
}
