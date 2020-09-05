package com.gmail.wigglewie.rsfinaltask.feature.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(link)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(imageUrl)
        parcel.writeString(pubDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NewsItem> {
        override fun createFromParcel(parcel: Parcel): NewsItem {
            return NewsItem(parcel)
        }

        override fun newArray(size: Int): Array<NewsItem?> {
            return arrayOfNulls(size)
        }
    }
}
