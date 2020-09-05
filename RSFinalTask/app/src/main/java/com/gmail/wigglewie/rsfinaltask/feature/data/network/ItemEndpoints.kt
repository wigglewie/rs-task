package com.gmail.wigglewie.rsfinaltask.feature.data.network

import retrofit2.Call
import retrofit2.http.GET

interface ItemEndpoints {

    @GET("/feed/subscribe/en/news/all/rss.xml")
    fun getTopStories(): Call<ApiXmlData>

    @GET("/feed/subscribe/en/news/region/middle-east/feed/rss.xml")
    fun getMiddleEast(): Call<ApiXmlData>

    @GET("/feed/subscribe/en/news/region/africa/feed/rss.xml")
    fun getAfrica(): Call<ApiXmlData>

    @GET("/feed/subscribe/en/news/region/europe/feed/rss.xml")
    fun getEurope(): Call<ApiXmlData>

    @GET("/feed/subscribe/en/news/region/americas/feed/rss.xml")
    fun getAmericas(): Call<ApiXmlData>

    @GET("/feed/subscribe/en/news/region/asia-pacific/feed/rss.xml")
    fun getAsiaPacific(): Call<ApiXmlData>

    @GET("/feed/subscribe/en/news/topic/health/feed/rss.xml")
    fun getHealth(): Call<ApiXmlData>

    @GET("/feed/subscribe/en/news/topic/un-affairs/feed/rss.xml")
    fun getUNAffairs(): Call<ApiXmlData>

    @GET("/feed/subscribe/en/news/topic/law-and-crime-prevention/feed/rss.xml")
    fun getLawAndCrime(): Call<ApiXmlData>

    @GET("/feed/subscribe/en/news/topic/human-rights/feed/rss.xml")
    fun getHumanRights(): Call<ApiXmlData>

    @GET("/feed/subscribe/en/news/topic/humanitarian-aid/feed/rss.xml")
    fun getHumanitarianAid(): Call<ApiXmlData>

    @GET("/feed/subscribe/en/news/topic/climate-change/feed/rss.xml")
    fun getClimateChange(): Call<ApiXmlData>

    @GET("/feed/subscribe/en/news/topic/culture-and-education/feed/rss.xml")
    fun getCultureAndEducation(): Call<ApiXmlData>

    @GET("/feed/subscribe/en/news/topic/economic-development/feed/rss.xml")
    fun getEconomicDevelopment(): Call<ApiXmlData>

    @GET("/feed/subscribe/en/news/topic/women/feed/rss.xml")
    fun getWomen(): Call<ApiXmlData>

    @GET("/feed/subscribe/en/news/topic/peace-and-security/feed/rss.xml")
    fun getPeaceAndSecurity(): Call<ApiXmlData>

    @GET("/feed/subscribe/en/news/topic/migrants-and-refugees/feed/rss.xml")
    fun getMigrantsAndRefugees(): Call<ApiXmlData>

    @GET("/feed/subscribe/en/news/topic/sdgs/feed/rss.xml")
    fun getSDGs(): Call<ApiXmlData>
}
