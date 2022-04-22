package com.nadikarim.submission2.ui.story.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nadikarim.submission2.data.StoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class AddStoryViewModel @Inject constructor(private val repository: StoryRepository): ViewModel() {

    val toastMessage: LiveData<String> = repository.toastMessage

    fun addStory(token: String, imageMultipart: File, description: String) {
        repository.addStory(token, imageMultipart, description)
    }

    fun addStory(token: String, imageMultipart: MultipartBody.Part, description: RequestBody, latitude: Float, longitude: Float) {
        repository.postStoryWithLOcation(token, imageMultipart, description, latitude, longitude)
    }
}