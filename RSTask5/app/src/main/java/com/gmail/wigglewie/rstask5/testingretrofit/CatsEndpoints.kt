package com.gmail.wigglewie.rstask5.testingretrofit

import com.gmail.wigglewie.rstask5.data.Cat
import com.squareup.moshi.Json
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CatsEndpoints {

    @GET("/v1/images/search?page=1&order=Desc&=0948292f-0aef-44d9-afbb-c341f4ae37a9&limit=10")
    fun getCats(): Call<List<Cat>>

}
