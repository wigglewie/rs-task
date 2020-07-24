package com.gmail.wigglewie.rstask5.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CatServiceBuilder {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}
