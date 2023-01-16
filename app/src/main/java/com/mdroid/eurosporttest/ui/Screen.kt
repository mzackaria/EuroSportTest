package com.mdroid.eurosporttest.ui

sealed class Screen(val route: String) {
    object MainScreen: Screen("main_screen")
    object StoryScreen: Screen("story_screen") {

        fun getFinalRoute() = buildString {
                append(route)
                append("?title={title}")
                append("?author={author}")
                append("?urlImage={urlImage}")
                append("?teaser={teaser}")
                append("?category={category}")
                append("?since={since}")
            }

        fun withArgs(
            title: String,
            author: String,
            urlImage: String,
            teaser: String,
            category: String,
            since: String
        ): String {
            return buildString {
                append(route)
                append("?title=$title")
                append("?author=$author")
                append("?urlImage=$urlImage")
                append("?teaser=$teaser")
                append("?category=$category")
                append("?since=$since")
            }
        }
    }
    object VideoScreen: Screen("video_screen") {

        fun getFinalRoute() = buildString {
            append(route)
            append("?url={url}")
        }

        fun withArgs(
            url: String
        ): String {
            return buildString {
                append(route)
                append("?url=$url")
            }
        }
    }
}