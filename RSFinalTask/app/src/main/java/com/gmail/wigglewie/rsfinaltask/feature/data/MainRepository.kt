package com.gmail.wigglewie.rsfinaltask.feature.data

import android.app.Application
import com.gmail.wigglewie.rsfinaltask.feature.data.database.LocalData
import com.gmail.wigglewie.rsfinaltask.feature.data.database.NewsItemDao
import com.gmail.wigglewie.rsfinaltask.feature.data.network.NetworkData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainRepository @Inject constructor(
    application: Application
) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var networkData: NetworkData = NetworkData()
    private var localData: LocalData = LocalData()
    private var newsItemDao: NewsItemDao

    init {
        val db = localData.getLocalData(application)
        newsItemDao = db.newsItemDao()
        println()
    }

    fun getLocalInfo() = newsItemDao.getAll()

    fun addNewsItem(newsItem: NewsItem?) {
//        var resultAfterAdding = false
        launch {
            addNewsItemBG(newsItem)
        }
    }

    private suspend fun addNewsItemBG(newsItem: NewsItem?) {
        var exists = true
        withContext(Dispatchers.IO) {
            if (newsItem != null) {
                exists = newsItemDao.exists(newsItem.link)
            }
            println()
        }
        if (!exists) {
            withContext(Dispatchers.IO) {
                newsItemDao.insert(newsItem)
                println()
            }
        } else {
            println()
        }
    }

    fun removeNewsItem(newsItem: NewsItem?) {
        launch {
            removeNewsItemBG(newsItem)
        }
    }

    private suspend fun removeNewsItemBG(newsItem: NewsItem?) {
        var exists = false
        withContext(Dispatchers.IO) {
            if (newsItem != null) {
                exists = newsItemDao.exists(newsItem.link)
            }
            println()
        }
        if (exists) {
            withContext(Dispatchers.IO) {
                newsItemDao.remove(newsItem)
                println()
            }
        } else {
            println()
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
