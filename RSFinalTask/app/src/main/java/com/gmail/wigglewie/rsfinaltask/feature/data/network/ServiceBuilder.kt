package com.gmail.wigglewie.rsfinaltask.feature.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object ServiceBuilder {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://news.un.org")
        .client(OkHttpClient())
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .build()

    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}
