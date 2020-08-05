package com.gmail.wigglewie.rstask6.model

import android.util.Log
import com.gmail.wigglewie.rstask6.contract.MainActivityContract
import com.gmail.wigglewie.rstask6.data.ApiData
import com.gmail.wigglewie.rstask6.data.DataItem
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.io.InputStream

class MainActivityModel : MainActivityContract.Model {

    override fun loadJsonData(inputStream: InputStream): MutableList<DataItem> {

        val moshi: Moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<ApiData> = moshi.adapter(ApiData::class.java)
        val buffer = ByteArray(inputStream.available())
        inputStream.read(buffer)
        val jsonString = String(buffer)
        val data = adapter.fromJson(jsonString)
        inputStream.close()

        val itemsParsed = data?.channel?.items
        var title: String?
        var videoDuration: String?
        var speaker: String
        val speakerList = mutableListOf<String>()
        val items = itemsParsed?.map { items ->
            val speakerArray = items.group?.credit
            title = items.title?.substringBefore(" |")
            videoDuration = items.duration?.videoDuration?.substringAfter("00:")
            if (speakerArray is ArrayList<*>) {
                speakerArray.forEach { i ->
                    val speakerName = i.toString().substringAfter("text=").substringBefore('}')
                    speakerList.add(speakerName)
                }
                if (speakerList.size == 2) {
                    speaker = speakerList.joinToString(" and ")
                    speakerList.clear()
                } else {
                    val speakerLast = speakerList.last()
                    speakerList.remove(speakerLast)
                    speaker = speakerList.joinToString(", ") + " and $speakerLast"
                    speakerList.clear()
                }
                println()
            } else {
                speaker = speakerArray.toString().substringAfter("text=").substringBefore('}')
            }
            DataItem(
                title,
                speaker,
                items.image?.imageUrl,
                videoDuration,
                items.description,
                items.video?.videoUrl
            )
        }

        Log.d("model", "loaded")

        return items!!.toMutableList()
    }

    override fun loadXmlData(): MutableList<DataItem> {

        return mutableListOf()
    }
}
