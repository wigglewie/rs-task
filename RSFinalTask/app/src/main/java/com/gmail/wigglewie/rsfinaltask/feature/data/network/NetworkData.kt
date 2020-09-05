package com.gmail.wigglewie.rsfinaltask.feature.data.network

import android.util.Log
import com.gmail.wigglewie.rsfinaltask.R
import com.gmail.wigglewie.rsfinaltask.feature.data.NewsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NetworkData @Inject constructor() {

    fun getNetworkData(
        topicName: Int,
        callback: (items: MutableList<NewsItem>?, msg: String) -> Unit
    ) {

        val request = ServiceBuilder.buildService(ItemEndpoints::class.java)
        var call: Call<ApiXmlData> = request.getTopStories()
        when (topicName) {
            R.string.topic_top_stories -> {
                call = request.getTopStories()
            }
            R.string.topic_middle_east -> {
                call = request.getMiddleEast()
            }
            R.string.topic_africa -> {
                call = request.getAfrica()
            }
            R.string.topic_europe -> {
                call = request.getEurope()
            }
            R.string.topic_americas -> {
                call = request.getAmericas()
            }
            R.string.topic_asia_pacific -> {
                call = request.getAsiaPacific()
            }
            R.string.topic_health -> {
                call = request.getHealth()
            }
            R.string.topic_un_affairs -> {
                call = request.getUNAffairs()
            }
            R.string.topic_law_and_crime_prevention -> {
                call = request.getLawAndCrime()
            }
            R.string.topic_human_rights -> {
                call = request.getHumanRights()
            }
            R.string.topic_humanitarian_aid -> {
                call = request.getHumanitarianAid()
            }
            R.string.topic_climate_change -> {
                call = request.getClimateChange()
            }
            R.string.topic_culture_and_education -> {
                call = request.getCultureAndEducation()
            }
            R.string.topic_economic_development -> {
                call = request.getEconomicDevelopment()
            }
            R.string.topic_women -> {
                call = request.getWomen()
            }
            R.string.topic_peace_and_security -> {
                call = request.getPeaceAndSecurity()
            }
            R.string.topic_migrants_and_refugees -> {
                call = request.getMigrantsAndRefugees()
            }
            R.string.topic_sdgs -> {
                call = request.getSDGs()
            }
        }

        var items: List<NewsItem>? = null

        call.enqueue(object : Callback<ApiXmlData> {

            override fun onResponse(
                call: Call<ApiXmlData>,
                response: Response<ApiXmlData>
            ) {
                val body = response.body()
                items = body?.channel?.itemList?.map { item ->
                    val pubDate = item.pubDate
                    NewsItem(
                        item.link,
                        item.title,
                        item.description?.trimEnd(),
                        item.imageData?.imageUrl,
                        pubDate?.substring(0, pubDate.length.minus(15))
                    )
                }

                callback(items?.toMutableList(), "SUCCESS")
//                callback(items?.toMutableList(), "ERROR")
                Log.d("xml--------------", "LOADED")
            }

            override fun onFailure(call: Call<ApiXmlData>, t: Throwable) {
                callback(items?.toMutableList(), "ERROR")
                Log.d("xml--------------", t.toString())
            }
        })
    }
}
