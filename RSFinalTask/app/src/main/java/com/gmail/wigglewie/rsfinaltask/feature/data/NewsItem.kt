package com.gmail.wigglewie.rsfinaltask.feature.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "news_items_favorites")
data class NewsItem(
    @PrimaryKey
    val link: String,
    @ColumnInfo
    val title: String?,
    @ColumnInfo
    val description: String?,
    @ColumnInfo
    val imageUrl: String?,
    @ColumnInfo
    val pubDate: String?
) : Parcelable
