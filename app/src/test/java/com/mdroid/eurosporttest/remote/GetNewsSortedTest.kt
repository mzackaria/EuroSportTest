package com.mdroid.eurosporttest.remote

import com.mdroid.eurosporttest.local.data.News
import com.mdroid.eurosporttest.remote.data.NewsRemote
import com.mdroid.eurosporttest.remote.data.StoryRemote
import com.mdroid.eurosporttest.remote.data.VideoRemote
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class GetNewsSortedTest {
     val video = VideoRemote(
         date = 0.0,
         id = 0,
         sport = null,
         thumb = null,
         title = null,
         url = null,
         views = null
     )
     val story = StoryRemote(
         author = null,
         date = 0.0,
         id = 0,
         image = null,
         sport = null,
         teaser = null,
         title = null
     )
     val video1 = VideoRemote(
         date = 1.0,
         id = 2,
         sport = null,
         thumb = null,
         title = null,
         url = null,
         views = null
     )
     val story1 = StoryRemote(
         author = null,
         id = 1,
         date = 1.0,
         image = null,
         sport = null,
         teaser = null,
         title = null
     )
     val story2 = StoryRemote(
         author = null,
         id = 2,
         date = 2.0,
         image = null,
         sport = null,
         teaser = null,
         title = null
     )

     @Test
     fun test_sortVideosAndStories_Correct() = runBlocking{
         val stories = listOf<StoryRemote>(story1, story2, story)
         val videos = listOf<VideoRemote>(video1, video)
         val getNewsSorted = GetNewsSorted(MockNewsApiService(stories, videos))
         val listResult = getNewsSorted()
         Assert.assertEquals(listResult.size, 5)

         //assert correct entanglement between news and videos
         Assert.assertTrue(listResult[0] is News.Video)
         Assert.assertTrue(listResult[1] is News.Story)
         Assert.assertTrue(listResult[2] is News.Video)
         Assert.assertTrue(listResult[3] is News.Story)
         Assert.assertTrue(listResult[4] is News.Story)

         //assert correct order of videos
         Assert.assertEquals((listResult[0] as News.Video).date ?: -1.0, video1.date)
         Assert.assertEquals((listResult[2] as News.Video).date ?: -1.0, video.date)

         //assert correct order of stories
         Assert.assertEquals((listResult[1] as News.Story).date ?: -1.0, story2.date)
         Assert.assertEquals((listResult[3] as News.Story).date ?: -1.0, story1.date)
         Assert.assertEquals((listResult[4] as News.Story).date ?: -1.0, story.date)
     }

    @Test
    fun test_sortVideosAndStories_OneDateIsNull() = runBlocking{
        val video3 = VideoRemote(
            date = null,
            id = 2,
            sport = null,
            thumb = null,
            title = null,
            url = null,
            views = null
        )
        val stories = listOf<StoryRemote>(story1, story2, story)
        val videos = listOf<VideoRemote>(video1, video, video3)
        val getNewsSorted = GetNewsSorted(MockNewsApiService(stories, videos))
        val listResult = getNewsSorted()
        Assert.assertEquals(listResult.size, 6)

        //assert correct entanglement between news and videos
        Assert.assertTrue(listResult[0] is News.Video)
        Assert.assertTrue(listResult[1] is News.Story)
        Assert.assertTrue(listResult[2] is News.Video)
        Assert.assertTrue(listResult[3] is News.Story)
        Assert.assertTrue(listResult[4] is News.Video)
        Assert.assertTrue(listResult[5] is News.Story)

        //assert correct order of videos
        Assert.assertEquals((listResult[0] as News.Video).date ?: -1.0, video1.date)
        Assert.assertEquals((listResult[2] as News.Video).date ?: -1.0, video.date)
        Assert.assertNull((listResult[4] as News.Video).date)

        //assert correct order of stories
        Assert.assertEquals((listResult[1] as News.Story).date ?: -1.0, story2.date)
        Assert.assertEquals((listResult[3] as News.Story).date ?: -1.0, story1.date)
        Assert.assertEquals((listResult[5] as News.Story).date ?: -1.0, story.date)
    }

 }

class MockNewsApiService(
    private val stories: List<StoryRemote>,
    private val videos: List<VideoRemote>,
): NewsApiService {
    override suspend fun getNews(): NewsRemote {

        return NewsRemote(
            stories = stories,
            videos = videos
        )
    }
}