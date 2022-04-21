package com.nadikarim.submission2.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nadikarim.submission2.data.remote.ApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
class StoryRepositoryTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var apiService: ApiService
    private lateinit var storyRepository: StoryRepository

    @Before
    fun setup() {
        apiService = FakeApiService()
        //storyRepository = StoryRepository(apiService)
    }
}