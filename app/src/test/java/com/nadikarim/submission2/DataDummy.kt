package com.nadikarim.submission2

import com.nadikarim.submission2.data.model.stories.StoriesResponse
import com.nadikarim.submission2.data.model.stories.Story
import retrofit2.Response

object DataDummy {

    fun generateDummyNewsEntity(): List<Story> {
        val newsList = ArrayList<Story>()
        for (i in 0..10) {
            val news = Story(
                "Title $i",
                "2022-02-22T22:22:22Z",
                "https://dicoding-web-img.sgp1.cdn.digitaloceanspaces.com/original/commons/feature-1-kurikulum-global-3.png",
                "https://www.dicoding.com/",
                "asd",
                0.0,
                0.0
            )
            newsList.add(news)
        }
        return newsList
    }

    fun generateDummyStoriesResponse(): StoriesResponse {
        val storyList = ArrayList<Story>()
        for (i in 0..10) {
            val story = Story(
                "2022",
                "description $i",
                "$i",
                "name $i",
                "url",
                10.10,
                10.10
            )
            storyList.add(story)
        }
        return StoriesResponse(false, storyList, "Success")
    }
}