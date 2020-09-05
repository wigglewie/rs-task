package com.gmail.wigglewie.rsfinaltask.feature.data

import com.gmail.wigglewie.rsfinaltask.feature.data.database.LocalData
import com.gmail.wigglewie.rsfinaltask.feature.data.network.NetworkData
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val localData: LocalData,
    private val remoteData: NetworkData
) {

    var list: MutableList<NewsItem>? = null
    var text = "no"

//    fun getNetworkInfo(link: String) = remoteData.getNetworkData()

    fun getNetworkInfo(
        topic: Int,
        callback: (items: MutableList<NewsItem>?, result: String) -> Unit
    ) {
        remoteData.getNetworkData(topic) { items, result ->
            callback(items, result)
        }
    }

    fun getLocalInfo() = localData.getLocalData()
}
