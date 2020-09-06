package com.gmail.wigglewie.rsfinaltask.feature.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gmail.wigglewie.rsfinaltask.feature.data.NewsItem

@Dao
interface NewsItemDao {

    @Query("SELECT * FROM news_items_favorites")
    fun getAll(): LiveData<MutableList<NewsItem>>

    @Insert
    fun insertAll(newsItems: MutableList<NewsItem>)

    @Insert
    fun insert(newsItem: NewsItem?)

    @Delete
    fun remove(newsItem: NewsItem?)

    @Query("SELECT EXISTS (SELECT 1 FROM news_items_favorites WHERE link = :link)")
    fun exists(link: String): Boolean
}
