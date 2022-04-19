package com.nadikarim.submission2.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nadikarim.submission2.data.model.stories.Story
import dagger.hilt.android.qualifiers.ApplicationContext

@Database(
    entities = [Story::class],
    version = 1,
    exportSchema = false
)
abstract class StoryDatabase() : RoomDatabase(){

    abstract fun storyDao(): StoryDao
}