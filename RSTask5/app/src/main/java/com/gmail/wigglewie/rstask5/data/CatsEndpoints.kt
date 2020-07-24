package com.gmail.wigglewie.rstask5.data

import retrofit2.Call
import retrofit2.http.GET

interface CatsEndpoints {

    @GET("/v1/images/search?page=1&order=Desc&=0948292f-0aef-44d9-afbb-c341f4ae37a9&limit=20")
    fun getCats(): Call<List<Cat>>
}
