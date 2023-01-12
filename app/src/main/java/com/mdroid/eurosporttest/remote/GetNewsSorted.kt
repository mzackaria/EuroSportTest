package com.mdroid.eurosporttest.remote

import com.mdroid.eurosporttest.local.data.News

class GetNewsSorted(
    private val repository: NewsApiService
) {

    suspend operator fun invoke(): List<News> {

        val newsRemote = repository.getNews()

        //sort videos by date and transform it to local model
        val videos = newsRemote.videos
            ?.sortedBy { videoRemote -> videoRemote.date }
            ?.map { it.toLocalModel() }
            ?: emptyList()

        //sort stories by date and transform it to local model
        val stories = newsRemote.stories
            ?.sortedBy { storyRemote -> storyRemote.date }
            ?.map { it.toLocalModel() }
            ?: emptyList()

        return zipNews(videos, stories)
    }

    private fun zipNews(videos: List<News>, stories: List<News>): List<News> {
        return videos.zip(stories).flatMap { listOf(it.first, it.second) } + if (videos.size > stories.size) videos.drop(stories.size) else stories.drop(videos.size)
    }
}