package com.nadikarim.submission2.ui.story.add

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.google.common.truth.Truth
import com.nadikarim.submission2.data.StoryRepository
import com.nadikarim.submission2.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.io.File

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AddStoryViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Mock
    private lateinit var viewModel: AddStoryViewModel
    @Mock
    private lateinit var mockFile: File

    @Test
    fun `kalo fungsi addStory jalan`() {
        val token = "token"
        val expectedData = MutableLiveData<String>()
        expectedData.value = "listStoryDummy"

        viewModel.addStory(token, mockFile, "sadasd")

        Mockito.verify(viewModel).addStory(token, mockFile, "sadasd")

        Mockito.`when`(viewModel.toastMessage).thenReturn(expectedData)

        val actualDAta = viewModel.toastMessage.getOrAwaitValue()

        Mockito.verify(viewModel).toastMessage
        Truth.assertThat(actualDAta).isEqualTo(expectedData.value)
    }

    @Test
    fun `kalo variable toast jalan`() {
        val expectedData = MutableLiveData<String>()
        expectedData.value = "true"

        Mockito.`when`(viewModel.toastMessage).thenReturn(expectedData)

        val actualDAta = viewModel.toastMessage.getOrAwaitValue()

        Mockito.verify(viewModel).toastMessage
        Truth.assertThat(actualDAta).isEqualTo(expectedData.value)
    }
}