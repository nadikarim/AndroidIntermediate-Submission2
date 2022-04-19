package com.nadikarim.submission2.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nadikarim.submission2.data.StoryRepository

class RegisterViewModel(private val repository: StoryRepository) : ViewModel() {

    val isLoading: LiveData<Boolean> = repository.isLoading

    fun registerUser(name: String, email: String,password: String) {
        repository.registerUser(name, email, password)
    }

}