package com.nadikarim.submission2.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.recyclerview.widget.ListUpdateCallback
import com.google.common.truth.Truth.assertThat
import com.nadikarim.submission2.DataDummy
import com.nadikarim.submission2.MainCoroutineRule
import com.nadikarim.submission2.data.model.login.LoginResult
import com.nadikarim.submission2.data.model.stories.Story
import com.nadikarim.submission2.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.io.File

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class StoryRepositoryTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    @Mock
    private lateinit var storyRepository: StoryRepository
    @Mock
    private lateinit var mockFile: File

    /**
     * ***Ini buat Variabel***
     */

    @Test
    fun `when login variable should return the right data`() {
        val loginREsult = LoginResult("asd", "asd", "asd")
        val expectedData = MutableLiveData<LoginResult>()
        expectedData.value = loginREsult

        `when`(storyRepository.userLogin).thenReturn(expectedData)

        val actualDAta = storyRepository.userLogin.getOrAwaitValue()

        verify(storyRepository).userLogin
        assertThat(actualDAta).isEqualTo(expectedData.value)
    }



    @Test
    fun `when loading value should return the right data`() {
        val expectedData = MutableLiveData<Boolean>()
        expectedData.value = true

        `when`(storyRepository.isLoading).thenReturn(expectedData)

        val actualDAta = storyRepository.isLoading.getOrAwaitValue()

        verify(storyRepository).isLoading
        assertThat(actualDAta).isEqualTo(expectedData.value)
    }

    @Test
    fun `when toast message should return the right data`() {
        val expectedData = MutableLiveData<String>()
        expectedData.value = "String test"

        `when`(storyRepository.toastMessage).thenReturn(expectedData)

        val actualDAta = storyRepository.toastMessage.getOrAwaitValue()

        verify(storyRepository).toastMessage
        assertThat(actualDAta).isEqualTo(expectedData.value)
    }

    @Test
    fun `when listStory should return the right data`() {
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
    fun `when Get Story Should Not Null`() = mainCoroutineRule.runBlockingTest {
        val dummyStory = DataDummy.generateDummyStoryResponse()
        val data = PagedTestDataSources.snapshot(dummyStory)
        val story = MutableLiveData<PagingData<Story>>()
        story.value = data
        `when`(storyRepository.getStory()).thenReturn(story)
        val actualData = storyRepository.getStory().getOrAwaitValue()

        val differ = AsyncPagingDataDiffer(
            diffCallback = ListAdapter.DIFF_CALLBACK,
            updateCallback = noopListUpdateCallback,
            mainDispatcher = mainCoroutineRule.dispatcher,
            workerDispatcher = mainCoroutineRule.dispatcher,
        )
        differ.submitData(actualData)

        advanceUntilIdle()
        verify(storyRepository).getStory()
        assertThat(differ.snapshot()).isNotNull()
        assertThat(dummyStory.size).isEqualTo(differ.snapshot().size)
        assertThat(dummyStory[0].name).isEqualTo(differ.snapshot()[0]?.name)

    }

    @Test
    fun `verify the login function is works`() {
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
    fun `verify the register function is works`() {
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
    fun `verify the getStoryWithLocation function is works`() {
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
    fun `verify the addStory function is works`() {
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

class PagedTestDataSources :
    PagingSource<Int, LiveData<List<Story>>>() {
    companion object {
        fun snapshot(items: List<Story>): PagingData<Story> {
            return PagingData.from(items)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, LiveData<List<Story>>>): Int {
        return 0
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LiveData<List<Story>>> {
        return LoadResult.Page(emptyList(), 0 , 1)
    }
}

val noopListUpdateCallback = object : ListUpdateCallback {
    override fun onInserted(position: Int, count: Int) {}
    override fun onRemoved(position: Int, count: Int) {}
    override fun onMoved(fromPosition: Int, toPosition: Int) {}
    override fun onChanged(position: Int, count: Int, payload: Any?) {}
}