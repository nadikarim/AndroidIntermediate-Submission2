package com.nadikarim.submission2.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.google.common.truth.Truth.assertThat
import com.nadikarim.submission2.data.model.UserSession
import com.nadikarim.submission2.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DataStoreViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var viewModel: DataStoreViewModel

    @Test
    fun `get session should not null and success`() {
        val dummy = UserSession("asd", "asdsad", "asd", true)
        val expectedData = MutableLiveData<UserSession>()
        expectedData.value = dummy

        `when`(viewModel.getSession()).thenReturn(expectedData)
        val actualData = viewModel.getSession().getOrAwaitValue()

        verify(viewModel).getSession()
        assertThat(actualData).isNotNull()
        assertThat(expectedData.value).isEqualTo(actualData)
    }

    @Test
    fun `verify the setSession function is works`() {
        val dummySession = UserSession("asd","asd","asd", true)
        val data = viewModel.setSession(dummySession)
        verify(viewModel).setSession(dummySession)
        assertThat(data).isNotNull()
    }

    @Test
    fun `verify the logout function is works`() {
        viewModel.logout()
        verify(viewModel).logout()
    }
}