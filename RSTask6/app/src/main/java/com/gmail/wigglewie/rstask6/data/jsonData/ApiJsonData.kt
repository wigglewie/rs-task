package com.gmail.wigglewie.rstask6.data.jsonData

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiJsonData(
    @Json(name = "channel") val channel: Channel
)

@JsonClass(generateAdapter = true)
data class Channel(
    @Json(name = "item") val items: List<Item>?
)

@JsonClass(generateAdapter = true)
data class Item(
    @Json(name = "title") val title: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "image") val image: Image?,
    @Json(name = "duration") val duration: Duration?,
    @Json(name = "enclosure") val video: Video?,
    @Json(name = "group") val group: Group?
)

@JsonClass(generateAdapter = true)
data class Image(
    @Json(name = "url") val imageUrl: String?
)

@JsonClass(generateAdapter = true)
data class Duration(
    @Json(name = "text") val videoDuration: String?
)

@JsonClass(generateAdapter = true)
data class Video(
    @Json(name = "url") val videoUrl: String?
)

@JsonClass(generateAdapter = true)
data class Group(
    @Json(name = "credit") var credit: Any?
)
