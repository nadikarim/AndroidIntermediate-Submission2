package com.nadikarim.submission2.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadikarim.submission2.data.StoryRepository
import com.nadikarim.submission2.data.model.UserSession
import com.nadikarim.submission2.data.model.login.LoginResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: StoryRepository) : ViewModel() {

    val userLogin: LiveData<LoginResult> = repository.userLogin

    val toastMessage: LiveData<String> = repository.toastMessage

    val isLoading: LiveData<Boolean> = repository.isLoading

    fun loginUser(email: String, password: String) = repository.loginUser(email, password)

}