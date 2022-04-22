package com.nadikarim.submission2.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nadikarim.submission2.data.model.stories.StoriesResponse
import com.nadikarim.submission2.data.model.stories.Story
import com.nadikarim.submission2.data.remote.ApiService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


class StoryPagingSource @Inject constructor(private val userPreference: UserPreference, private val apiService: ApiService) : PagingSource<Int, Story>() {
    override fun getRefreshKey(state: PagingState<Int, Story>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Story> {
        return try {
            val page = params.key ?: INITIAL_PAGE_INDEX
            var token: String? = userPreference.getUser().first().token

            if (token != null) {
                val responseData = apiService.getListStory("Bearer $token", page, params.loadSize)
                if (responseData.isSuccessful) {
                    Log.d("tag", responseData.message())
                    return LoadResult.Page(
                        data = responseData.body()?.listStory ?: emptyList(),
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = if (responseData.body()?.listStory.isNullOrEmpty()) null else page + 1
                    )

                } else {
                    Log.d("tag", "ini catch")
                    return LoadResult.Error(Exception("Maaf gabisa"))
                }

            } else {
                return LoadResult.Error(Exception("Maaf gabisa"))
            }


        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }


}







