package com.nadikarim.submission2

import com.nadikarim.submission2.data.model.login.LoginResult
import com.nadikarim.submission2.data.model.stories.StoriesResponse
import com.nadikarim.submission2.data.model.stories.Story

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

    fun generateDummyStoryResponse2(): List<Story> {
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

    fun generateDummyLoginResulr(): List<LoginResult> {
        val items: MutableList<LoginResult> = arrayListOf()
        for (i in 0..100) {
            val loginResult = LoginResult(
                "nama $i",
                "token $i",
                "user $i"
            )
            items.add(loginResult)
        }
        return items
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