package com.nadikarim.submission2.ui.main

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nadikarim.submission2.data.StoryRepository
import com.nadikarim.submission2.data.model.UserSession
import com.nadikarim.submission2.data.model.stories.StoriesResponse
import com.nadikarim.submission2.data.model.stories.Story
import com.nadikarim.submission2.data.remote.ApiConfig
import com.nadikarim.submission2.utils.RETROFIT_TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainVIewModel @Inject constructor(private val repository: StoryRepository) : ViewModel(){

    val listStory: LiveData<ArrayList<Story>> = repository.listStory
    val isLoading: LiveData<Boolean> = repository.isLoading

    val story: LiveData<PagingData<Story>> = repository.getStory().cachedIn(viewModelScope)

    /*
    fun quote(token: String): LiveData<PagingData<Story>> {
        return repository.getStory("Bearer $token").cachedIn(viewModelScope)
    }
     */

    /*
    fun getStories(auth: String) {
        repository.getStory(auth)
    }

     */


    /*
    fun getUserSession(): LiveData<UserSession> {
        return repository.getUserSession().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }

     */

}