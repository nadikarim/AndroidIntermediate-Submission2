package com.nadikarim.submission2.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.google.common.truth.Truth
import com.nadikarim.submission2.DataDummy
import com.nadikarim.submission2.data.model.login.LoginResult
import com.nadikarim.submission2.data.model.stories.Story
import com.nadikarim.submission2.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Mock
    private lateinit var viewModel: LoginViewModel


    @Test
    fun `when userLogin variable should return the right data`() {

        val loginREsult = LoginResult("asd", "asd", "asd")
        val expectedData = MutableLiveData<LoginResult>()
        expectedData.value = loginREsult

        Mockito.`when`(viewModel.userLogin).thenReturn(expectedData)

        val actualDAta = viewModel.userLogin.getOrAwaitValue()

        Mockito.verify(viewModel).userLogin
        Truth.assertThat(actualDAta).isEqualTo(expectedData.value)
    }

    @Test
    fun `when loading value should return the right data`() {
        val expectedData = MutableLiveData<Boolean>()
        expectedData.value = true

        Mockito.`when`(viewModel.isLoading).thenReturn(expectedData)

        val actualDAta = viewModel.isLoading.getOrAwaitValue()

        Mockito.verify(viewModel).isLoading
        Truth.assertThat(actualDAta).isEqualTo(expectedData.value)
    }

    @Test
    fun `when toast message should return the right data`() {
        val expectedData = MutableLiveData<String>()
        expectedData.value = "true"

        Mockito.`when`(viewModel.toastMessage).thenReturn(expectedData)

        val actualDAta = viewModel.toastMessage.getOrAwaitValue()

        Mockito.verify(viewModel).toastMessage
        Truth.assertThat(actualDAta).isEqualTo(expectedData.value)
    }

    @Test
    fun `verify the login function is works`() {
        val email = "asd"
        val password = "asd"
        val listStoryDummy = DataDummy.generateDummyStoryResponse()
        val expectedData = MutableLiveData<List<Story>>()
        expectedData.value = listStoryDummy

        viewModel.loginUser(email, password)

        Mockito.verify(viewModel).loginUser(email, password)
    }

}