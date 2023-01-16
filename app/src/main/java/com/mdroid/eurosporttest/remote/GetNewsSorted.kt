package com.mdroid.eurosporttest.remote

import com.mdroid.eurosporttest.local.data.News

class GetNewsSorted(
    private val repository: NewsApiService
) {

    suspend operator fun invoke(): List<News> {
        val newsRemote = repository.getNews()

        //sort videos by date and transform it to local model
        val videos = newsRemote.videos
            ?.sortedByDescending { videoRemote -> videoRemote.date }
            ?.map { it.toLocalModel() }
            ?: emptyList()

        //sort stories by date and transform it to local model
        val stories = newsRemote.stories
            ?.sortedByDescending { storyRemote -> storyRemote.date }
            ?.map { it.toLocalModel() }
            ?: emptyList()

        return zipNews(videos, stories)
    }

    //turn the two lists into one list of items with mixed one-by-one items (story-video-story-video.....)
    private fun zipNews(videos: List<News>, stories: List<News>): List<News> {

        //the list with mixed one-by-one items (story-video-story-video.....)
        val list = videos.zip(stories).flatMap { listOf(it.first, it.second) }

        //the rest of the longest list
        val rest = stories.drop(videos.size)

        return  list + rest
    }
}