package com.nadikarim.submission2.ui.maps

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
class MapsViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Mock
    private lateinit var viewModel: MapsViewModel

    @Test
    fun `when listStory should return the right data`() {
        val listStoryDummy = DataDummy.generateDummyStoryResponse()
        val expectedData = MutableLiveData<List<Story>>()
        expectedData.value = listStoryDummy

        Mockito.`when`(viewModel.listStory).thenReturn(expectedData)

        val actualDAta = viewModel.listStory.getOrAwaitValue()

        Mockito.verify(viewModel).listStory
        Truth.assertThat(actualDAta).isEqualTo(expectedData.value)
    }


    @Test
    fun `verify the getAllStoryWithMaps function is works`() {
        val token = "string"
        val loginREsult = LoginResult("asd", "asd", "asd")
        val expectedData = MutableLiveData<LoginResult>()
        expectedData.value = loginREsult

        viewModel.getAllStoryWithMaps(token)

        Mockito.verify(viewModel).getAllStoryWithMaps(token)
    }
}