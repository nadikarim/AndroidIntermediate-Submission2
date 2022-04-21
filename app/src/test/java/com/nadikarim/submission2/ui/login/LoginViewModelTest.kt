package com.nadikarim.submission2.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nadikarim.submission2.DataDummy
import com.nadikarim.submission2.MainCoroutineRule
import com.nadikarim.submission2.data.StoryRepository
import com.nadikarim.submission2.data.model.login.LoginRequest
import com.nadikarim.submission2.data.remote.ApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var storyRepository: StoryRepository
    private lateinit var loginViewModel: LoginViewModel
    private val dummyDetailNews = DataDummy.generateDummyStoriesResponse()
    private lateinit var mockWebServer: MockWebServer

    private val service: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("http://localhost:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start(8080)
        loginViewModel = LoginViewModel(storyRepository)
    }

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Test
    fun `when login is success`() {

        val expectedLogin = ""
        val email = "asd"
        val password = "asd"

        service.loginUser(email, password)
        val actualUser = loginViewModel.loginUser(email, password)

        assertEquals(actualUser, "asd")
    }

}