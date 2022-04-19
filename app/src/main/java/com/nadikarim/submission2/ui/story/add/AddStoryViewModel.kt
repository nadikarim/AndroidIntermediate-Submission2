package com.nadikarim.submission2.ui.story.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nadikarim.submission2.data.StoryRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody

class AddStoryViewModel(private val repository: StoryRepository): ViewModel() {

    val toastMessage: LiveData<String> = repository.toastMessage

    fun addStory(token: String, imageMultipart: MultipartBody.Part, description: RequestBody) {
        repository.addStory(token, imageMultipart, description)
    }
}