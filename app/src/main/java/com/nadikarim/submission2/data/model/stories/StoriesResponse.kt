package com.nadikarim.submission2.data.model.stories

data class StoriesResponse(
    val error: Boolean,
    val listStory: List<Story>,
    val message: String
)