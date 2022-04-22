package com.nadikarim.submission2.ui.story.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nadikarim.submission2.data.StoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject

@HiltViewModel
class AddStoryViewModel @Inject constructor(private val repository: StoryRepository): ViewModel() {

    val toastMessage: LiveData<String> = repository.toastMessage

    fun addStory(token: String, imageMultipart: File, description: String) {
        repository.addStory(token, imageMultipart, description)
    }

}