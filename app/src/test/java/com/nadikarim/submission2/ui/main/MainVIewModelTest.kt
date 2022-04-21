package com.nadikarim.submission2.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nadikarim.submission2.DataDummy
import com.nadikarim.submission2.MainCoroutineRule
import com.nadikarim.submission2.data.StoryRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainVIewModelTest{
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var storyRepository: StoryRepository
    private lateinit var mainVIewModel: MainVIewModel
    private val dummyDetailNews = DataDummy.generateDummyStoriesResponse()

    @Before
    fun setUp() {
        mainVIewModel = MainVIewModel(storyRepository)
    }

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

}