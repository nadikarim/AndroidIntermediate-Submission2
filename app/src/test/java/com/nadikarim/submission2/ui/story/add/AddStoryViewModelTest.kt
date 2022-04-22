package com.nadikarim.submission2.ui.story.add

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.google.common.truth.Truth
import com.nadikarim.submission2.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
    fun `verify the addStory function is works`() {
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
    fun `when toast message should return the right data`() {
        val expectedData = MutableLiveData<String>()
        expectedData.value = "true"

        Mockito.`when`(viewModel.toastMessage).thenReturn(expectedData)

        val actualDAta = viewModel.toastMessage.getOrAwaitValue()

        Mockito.verify(viewModel).toastMessage
        Truth.assertThat(actualDAta).isEqualTo(expectedData.value)
    }
}