package com.nadikarim.submission2

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

}