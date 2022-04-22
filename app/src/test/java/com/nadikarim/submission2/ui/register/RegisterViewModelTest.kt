package com.nadikarim.submission2.ui.register

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.google.common.truth.Truth
import com.nadikarim.submission2.data.model.login.LoginResult
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
class RegisterViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Mock
    private lateinit var viewModel: RegisterViewModel

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
    fun `verify the register function is works`() {
        val name = "name"
        val email = "asd"
        val password = "asd"
        val loginREsult = LoginResult("asd", "asd", "asd")
        val expectedData = MutableLiveData<LoginResult>()
        expectedData.value = loginREsult

        viewModel.registerUser(name, email, password)

        Mockito.verify(viewModel).registerUser(name, email, password)
    }



}