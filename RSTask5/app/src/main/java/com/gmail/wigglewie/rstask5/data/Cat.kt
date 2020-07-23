package com.gmail.wigglewie.rstask5.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//data class Cat(val catLink: String?)

@JsonClass(generateAdapter = true)
data class Cat(
    @Json(name = "url") val url: String?
)
