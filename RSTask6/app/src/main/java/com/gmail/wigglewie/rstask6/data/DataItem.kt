package com.gmail.wigglewie.rstask6.data

import java.io.Serializable

data class DataItem(
    val title: String?,
    val speaker: String?,
    val imageUrl: String?,
    val videoDuration: String?,
    val description: String?,
    val videoUrl: String?
) : Serializable
