package com.gmail.wigglewie.rstask6.data.xmlData

import com.gmail.wigglewie.test1.ApiItems
import retrofit2.Call
import retrofit2.http.GET

interface ItemEndpoint {

    @GET("/themes/rss/id")
    fun getXmlData(): Call<ApiItems>
}
