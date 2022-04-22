package com.nadikarim.submission2.ui.main

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nadikarim.submission2.data.StoryRepository
import com.nadikarim.submission2.data.model.stories.Story
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainVIewModel @Inject constructor(repository: StoryRepository) : ViewModel(){

    val isLoading: LiveData<Boolean> = repository.isLoading

    val story: LiveData<PagingData<Story>> = repository.getStory().cachedIn(viewModelScope)

}