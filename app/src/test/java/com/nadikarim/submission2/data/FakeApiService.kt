package com.nadikarim.submission2.data

import com.nadikarim.submission2.DataDummy
import com.nadikarim.submission2.data.model.login.LoginResponse
import com.nadikarim.submission2.data.model.login.RegisterResponse
import com.nadikarim.submission2.data.model.stories.AddResponse
import com.nadikarim.submission2.data.model.stories.StoriesResponse
import com.nadikarim.submission2.data.remote.ApiService
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response

class FakeApiService : ApiService {
    private val dummyResponse = DataDummy.generateDummyStoriesResponse()
    override fun registerUser(
        name: String,
        email: String,
        password: String
    ): Call<RegisterResponse> {
        TODO("Not yet implemented")
    }

    override fun loginUser(email: String, password: String): Call<LoginResponse> {
        TODO("Not yet implemented")
    }

    override fun loginUser2(email: String, password: String): LoginResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getListStory(
        auth: String,
        page: Int,
        size: Int
    ): Response<StoriesResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getListStory2(auth: String, page: Int, size: Int): StoriesResponse {
        TODO("Not yet implemented")
    }

    override fun uploadImage(
        auth: String,
        file: MultipartBody.Part,
        description: RequestBody
    ): Call<AddResponse> {
        TODO("Not yet implemented")
    }

    override fun getListStoryWithLocation(auth: String, location: Int): Call<StoriesResponse> {
        TODO("Not yet implemented")
    }

    override fun getListStoryWithLocation2(auth: String, location: Int): StoriesResponse {
        TODO("Not yet implemented")
    }

    override fun uploadImageWithLocation(
        auth: String,
        file: MultipartBody.Part,
        description: RequestBody,
        latitude: Float,
        longitude: Float
    ): Call<AddResponse> {
        TODO("Not yet implemented")
    }
}