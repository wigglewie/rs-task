package com.gmail.wigglewie.rsfinaltask.feature.data

import android.app.Application
import com.gmail.wigglewie.rsfinaltask.feature.data.database.LocalData
import com.gmail.wigglewie.rsfinaltask.feature.data.database.NewsItemDao
import com.gmail.wigglewie.rsfinaltask.feature.data.network.NetworkData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepository @Inject constructor(
    application: Application
) {

    private var networkData: NetworkData = NetworkData()
    private var localData: LocalData = LocalData()
    private var newsItemDao: NewsItemDao

    init {
        val db = localData.getLocalData(application)
        newsItemDao = db.newsItemDao()
    }

    fun getLocalInfo() = newsItemDao.getAll()

    suspend fun addNewsItem(newsItem: NewsItem?) {
        var exists = true
        withContext(Dispatchers.IO) {
            if (newsItem != null) {
                exists = newsItemDao.exists(newsItem.link)
            }
        }
        if (!exists) {
            withContext(Dispatchers.IO) {
                newsItemDao.insert(newsItem)
            }
        }
    }

    suspend fun removeNewsItem(newsItem: NewsItem?) {
        var exists = false
        withContext(Dispatchers.IO) {
            if (newsItem != null) {
                exists = newsItemDao.exists(newsItem.link)
            }
        }
        if (exists) {
            withContext(Dispatchers.IO) {
                newsItemDao.remove(newsItem)
            }
        }
    }

    fun getNetworkInfo(
        topic: Int,
        callback: (items: MutableList<NewsItem>?, result: String) -> Unit
    ) {
        networkData.getNetworkData(topic) { items, result ->
            callback(items, result)
        }
    }
}
