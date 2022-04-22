package com.nadikarim.submission2.data

import com.nadikarim.submission2.data.model.stories.StoriesResponse
import com.nadikarim.submission2.data.model.stories.Story
import retrofit2.Response

object DataDummy {

    fun generateDummyStoryResponse(): List<Story> {
        val items: MutableList<Story> = arrayListOf()
        for (i in 0..100) {
            val quote = Story(
                "2022",
                "description $i",
                "$i",
                "name $i",
                "url",
                10.10,
                10.10
            )
            items.add(quote)
        }
        return items
    }

    fun generateDummyStoryList(): StoriesResponse {
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