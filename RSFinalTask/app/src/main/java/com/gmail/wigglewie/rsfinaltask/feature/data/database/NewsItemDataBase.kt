package com.gmail.wigglewie.rsfinaltask.feature.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gmail.wigglewie.rsfinaltask.feature.data.NewsItem

@Database(entities = [NewsItem::class], version = 1)
abstract class NewsItemDataBase : RoomDatabase() {
    abstract fun newsItemDao(): NewsItemDao
}
