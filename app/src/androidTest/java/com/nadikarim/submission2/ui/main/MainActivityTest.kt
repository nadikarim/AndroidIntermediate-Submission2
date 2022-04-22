package com.nadikarim.submission2.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.nadikarim.submission2.JsonConverter
import com.nadikarim.submission2.R
import com.nadikarim.submission2.utils.BASE_URL
import com.nadikarim.submission2.utils.EspressoIdlingResource
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class MainActivityTest {

    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    private val mockWebServer = MockWebServer()


    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        mockWebServer.start(8080)
        BASE_URL = "http://127.0.0.1:8080/"
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }
    @After
    fun tearDown() {
        mockWebServer.shutdown()
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun getListStory_Success() {
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(JsonConverter.readStringFromFile("response.json"))
        mockWebServer.enqueue(mockResponse)

        onView(withId(R.id.rv_story)).check(matches(isDisplayed()))

        onView(withId(R.id.rv_story)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))

    }

    @Test
    fun getListStory_ToMapView() {
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(JsonConverter.readStringFromFile("response.json"))
        mockWebServer.enqueue(mockResponse)

        onView(withId(R.id.menu_maps)).check(matches(isDisplayed()))
        onView(withId(R.id.menu_maps)).perform(click())

    }

}