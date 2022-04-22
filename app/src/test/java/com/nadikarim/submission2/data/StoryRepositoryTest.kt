package com.nadikarim.submission2.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.google.common.truth.Truth.assertThat
import com.nadikarim.submission2.DataDummy
import com.nadikarim.submission2.data.model.login.LoginResponse
import com.nadikarim.submission2.data.model.login.LoginResult
import com.nadikarim.submission2.data.model.stories.Story
import com.nadikarim.submission2.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.io.File

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class StoryRepositoryTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Mock
    private lateinit var storyRepository: StoryRepository
    @Mock
    private lateinit var mockFile: File

    /**
     * ***Ini buat Variabel***
     */

    @Test
    fun `kalo variable login jalan`() {
        val loginREsult = LoginResult("asd", "asd", "asd")
        val expectedData = MutableLiveData<LoginResult>()
        expectedData.value = loginREsult

        `when`(storyRepository.userLogin).thenReturn(expectedData)

        val actualDAta = storyRepository.userLogin.getOrAwaitValue()

        verify(storyRepository).userLogin
        assertThat(actualDAta).isEqualTo(expectedData.value)
    }



    @Test
    fun `kalo variable loding jalan`() {
        val expectedData = MutableLiveData<Boolean>()
        expectedData.value = true

        `when`(storyRepository.isLoading).thenReturn(expectedData)

        val actualDAta = storyRepository.isLoading.getOrAwaitValue()

        verify(storyRepository).isLoading
        assertThat(actualDAta).isEqualTo(expectedData.value)
    }

    @Test
    fun `kalo variable toast jalan`() {
        val expectedData = MutableLiveData<String>()
        expectedData.value = "String test"

        `when`(storyRepository.toastMessage).thenReturn(expectedData)

        val actualDAta = storyRepository.toastMessage.getOrAwaitValue()

        verify(storyRepository).toastMessage
        assertThat(actualDAta).isEqualTo(expectedData.value)
    }

    @Test
    fun `kalo variable listStory jalan`() {
        val listStoryDummy = DataDummy.generateDummyStoryResponse()
        val expectedData = MutableLiveData<List<Story>>()
        expectedData.value = listStoryDummy

        `when`(storyRepository.listStory).thenReturn(expectedData)

        val actualDAta = storyRepository.listStory.getOrAwaitValue()

        verify(storyRepository).listStory
        assertThat(actualDAta).isEqualTo(expectedData.value)
    }

    /**
     * ***Ini buat Fungsi***
     */

    @Test
    fun `kalo fungsi login jalan`() {
        val  email = "asd"
        val password = "asd"
        val loginREsult = LoginResult("asd", "asd", "asd")
        val expectedData = MutableLiveData<LoginResult>()
        expectedData.value = loginREsult

        storyRepository.loginUser(email, password)

        verify(storyRepository).loginUser(email, password)

        `when`(storyRepository.userLogin).thenReturn(expectedData)

        val actualDAta = storyRepository.userLogin.getOrAwaitValue()

        verify(storyRepository).userLogin
        assertThat(actualDAta).isEqualTo(expectedData.value)
    }

    @Test
    fun `kalo fungsi register jalan`() {
        val name = "name"
        val email = "asd"
        val password = "asd"
        val expectedData = MutableLiveData<Boolean>()
        expectedData.value = true

        storyRepository.registerUser(name, email, password)

        verify(storyRepository).registerUser(name, email, password)

        `when`(storyRepository.isLoading).thenReturn(expectedData)

        val actualDAta = storyRepository.isLoading.getOrAwaitValue()

        verify(storyRepository).isLoading
        assertThat(actualDAta).isEqualTo(expectedData.value)
    }

    @Test
    fun `kalo fungsi getStoryWithLocation jalan`() {
        val token = "token"
        val listStoryDummy = DataDummy.generateDummyStoryResponse()
        val expectedData = MutableLiveData<List<Story>>()
        expectedData.value = listStoryDummy

        storyRepository.getStoryWithLocation(token)

        verify(storyRepository).getStoryWithLocation(token)

        `when`(storyRepository.listStory).thenReturn(expectedData)

        val actualDAta = storyRepository.listStory.getOrAwaitValue()

        verify(storyRepository).listStory
        assertThat(actualDAta).isEqualTo(expectedData.value)
    }


    @Test
    fun `kalo fungsi addStory jalan`() {
        val token = "token"
        val expectedData = MutableLiveData<String>()
        expectedData.value = "listStoryDummy"

        storyRepository.addStory(token, mockFile, "sadasd")

        verify(storyRepository).addStory(token, mockFile, "sadasd")

        `when`(storyRepository.toastMessage).thenReturn(expectedData)

        val actualDAta = storyRepository.toastMessage.getOrAwaitValue()

        verify(storyRepository).toastMessage
        assertThat(actualDAta).isEqualTo(expectedData.value)
    }

}