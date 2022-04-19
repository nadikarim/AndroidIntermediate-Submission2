package com.nadikarim.submission2.ui.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nadikarim.submission2.data.StoryRepository
import com.nadikarim.submission2.data.model.stories.Story
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(private val repository: StoryRepository) : ViewModel() {

    val listStory: LiveData<List<Story>> = repository.listStory

    fun getAllStoryWithMaps(token: String) {
        repository.getStoryWithLocation("Bearer $token")
    }
}