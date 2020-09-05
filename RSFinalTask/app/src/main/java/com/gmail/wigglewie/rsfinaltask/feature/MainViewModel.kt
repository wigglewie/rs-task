package com.gmail.wigglewie.rsfinaltask.feature

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.gmail.wigglewie.rsfinaltask.feature.data.MainRepository
import com.gmail.wigglewie.rsfinaltask.feature.data.NewsItem

class MainViewModel @ViewModelInject constructor(
    application: Application
) : ViewModel() {

    private var mainRepository: MainRepository = MainRepository(application)

    fun getLocalData() = mainRepository.getLocalInfo()
    fun addNewsItem(newsItem: NewsItem?) {
        mainRepository.addNewsItem(newsItem)
    }

    fun removeNewsItem(newsItem: NewsItem?) {
        mainRepository.removeNewsItem(newsItem)
    }

    fun getNetworkData(
        topic: Int,
        callback: (items: MutableList<NewsItem>?, result: String) -> Unit
    ) {
        mainRepository.getNetworkInfo(topic) { items, result ->
            callback(items, result)
        }
    }
}
