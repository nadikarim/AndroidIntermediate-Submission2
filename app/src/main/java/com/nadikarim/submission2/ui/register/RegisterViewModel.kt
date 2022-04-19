package com.nadikarim.submission2.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nadikarim.submission2.data.StoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository: StoryRepository) : ViewModel() {

    val isLoading: LiveData<Boolean> = repository.isLoading

    fun registerUser(name: String, email: String,password: String) {
        repository.registerUser(name, email, password)
    }

}