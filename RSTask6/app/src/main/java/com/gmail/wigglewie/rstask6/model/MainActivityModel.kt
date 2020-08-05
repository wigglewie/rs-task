package com.gmail.wigglewie.rstask6.model

import android.util.Log
import com.gmail.wigglewie.rstask6.contract.MainActivityContract
import com.gmail.wigglewie.rstask6.data.ApiData
import com.gmail.wigglewie.rstask6.data.DataItem
import com.gmail.wigglewie.rstask6.data.xmlData.ItemEndpoint
import com.gmail.wigglewie.test1.ApiItems
import com.gmail.wigglewie.test1.main.ServiceBuilder
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

        val request = ServiceBuilder.buildService(ItemEndpoint::class.java)
        val call = request.getXmlData()

        var items: List<DataItem>? = null
        var isLoading = true

        call.enqueue(object : Callback<ApiItems> {

            override fun onResponse(
                call: Call<ApiItems>,
                response: Response<ApiItems>
            ) {
                val body = response.body()
                items = body?.channel?.itemList?.map { item ->
                    val title = item.title.substringBefore(" |")
                    val speaker = item.creditList?.speaker?.get("speaker")
                    val videoDuration = item.videoDuration.substringAfter("00:")
                    DataItem(
                        title,
                        speaker,
                        item.imageInfo?.imageUrl,
                        videoDuration,
                        item.description,
                        item.videoInfo?.videoUrl
                    )
                }
                isLoading = false
                val items1 = items
                Log.d("xml--------------", "LOADED")
                println()
            }

            override fun onFailure(call: Call<ApiItems>, t: Throwable) {
                println()
            }
        })

        return mutableListOf()
    }
}
