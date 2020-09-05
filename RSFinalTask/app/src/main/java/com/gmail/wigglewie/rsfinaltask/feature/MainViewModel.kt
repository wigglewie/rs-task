package com.gmail.wigglewie.rsfinaltask.feature

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.gmail.wigglewie.rsfinaltask.feature.data.MainRepository
import com.gmail.wigglewie.rsfinaltask.feature.data.NewsItem

class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    init {

//        Log.d("test", "MVVM and repo: ${myRepository.getNetworkInfo("")}")
    }

    fun getLocalData(): String {
        return mainRepository.getLocalInfo()
    }

    fun getNetworkData(
        topic: Int,
        callback: (items: MutableList<NewsItem>?, result: String) -> Unit
    ) {
        mainRepository.getNetworkInfo(topic) { items, result ->
            callback(items, result)
            println()
        }
    }
}
