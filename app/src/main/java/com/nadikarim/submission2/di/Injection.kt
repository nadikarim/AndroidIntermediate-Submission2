package com.nadikarim.submission2.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.nadikarim.submission2.data.StoryRepository
import com.nadikarim.submission2.data.local.database.StoryDatabase
import com.nadikarim.submission2.data.remote.ApiConfig

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

object Injection {
    fun provideRepository(context: Context): StoryRepository {
        val database = StoryDatabase.getDatabase(context)
        val apiService = ApiConfig.getApiService()
        //val dataStore = UserPreference.getInstance(context.dataStore)
        return StoryRepository(
            database,
            apiService,
            //dataStore
        )
    }
}