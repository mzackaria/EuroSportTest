package com.mdroid.eurosporttest.ui

sealed class Screen(val route: String) {
    object MainScreen: Screen("main_screen")
    object StoryScreen: Screen("story_screen") {
        fun withArgs(urlImage: String, teaser: String): String {
            return buildString {
                append(route)
                append("?urlImage={$urlImage}")
                append("?teaser={$teaser}")
            }
        }
    }
}