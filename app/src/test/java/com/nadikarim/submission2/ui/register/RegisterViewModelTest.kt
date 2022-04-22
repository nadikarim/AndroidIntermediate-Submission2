package com.nadikarim.submission2.ui.register

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.google.common.truth.Truth
import com.nadikarim.submission2.data.model.login.LoginResult
import com.nadikarim.submission2.getOrAwaitValue
import com.nadikarim.submission2.ui.login.LoginViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
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
    fun `kalo variabel loading jalan`() {
        val expectedData = MutableLiveData<Boolean>()
        expectedData.value = true

        Mockito.`when`(viewModel.isLoading).thenReturn(expectedData)

        val actualDAta = viewModel.isLoading.getOrAwaitValue()

        Mockito.verify(viewModel).isLoading
        Truth.assertThat(actualDAta).isEqualTo(expectedData.value)
    }


    @Test
    fun `kalo fungsi login jalan`() {
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