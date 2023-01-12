package com.mdroid.eurosporttest.remote

import com.mdroid.eurosporttest.local.data.News

class GetNewsSorted(
    private val repository: RemoteNewsRepository
) {

    suspend operator fun invoke(): List<News> {
        val listOfNews = mutableListOf<News>()
        listOfNews.apply {
            addAll(
                repository.getNews().videos?.map { videoRemote ->
                    News.Video(
                        date = videoRemote.date,
                        id = videoRemote.id,
                        sport = videoRemote.sport,
                        thumb = videoRemote.thumb,
                        title = videoRemote.title,
                        url = videoRemote.url,
                        views = videoRemote.views,
                    )
                } ?: emptyList()
            )
            addAll(
                repository.getNews().stories?.map { videoRemote ->
                    News.Story(
                        author = videoRemote.author,
                        date = videoRemote.date,
                        id = videoRemote.id,
                        image = videoRemote.image,
                        sport = videoRemote.sport,
                        teaser = videoRemote.teaser,
                        title = videoRemote.title,
                    )
                } ?: emptyList()
            )
        }
        listOfNews.sortBy { news ->
            news.date
        }
        return listOfNews
    }
}