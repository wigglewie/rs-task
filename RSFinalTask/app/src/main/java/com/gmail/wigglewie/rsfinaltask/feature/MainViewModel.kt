package com.gmail.wigglewie.rsfinaltask.feature

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.wigglewie.rsfinaltask.feature.data.MainRepository
import com.gmail.wigglewie.rsfinaltask.feature.data.NewsItem
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    application: Application,
) : ViewModel() {

    private var mainRepository: MainRepository = MainRepository(application)

    fun getLocalData() = mainRepository.getLocalInfo()

    fun addNewsItem(newsItem: NewsItem?) {
        viewModelScope.launch {
            mainRepository.addNewsItem(newsItem)
        }
    }

    fun removeNewsItem(newsItem: NewsItem?) {
        viewModelScope.launch {
            mainRepository.removeNewsItem(newsItem)
        }
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
