package com.nadikarim.submission2.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nadikarim.submission2.data.local.entity.RemoteKeys
import com.nadikarim.submission2.data.model.stories.Story

@Database(
    entities = [Story::class, RemoteKeys::class],
    version = 2,
    exportSchema = false
)
abstract class StoryDatabase : RoomDatabase(){

    abstract fun storyDao(): StoryDao

    abstract fun remoteKeysDao(): RemoteKeysDao
}