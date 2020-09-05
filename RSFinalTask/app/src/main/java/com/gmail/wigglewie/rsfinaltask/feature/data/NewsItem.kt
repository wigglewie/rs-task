package com.gmail.wigglewie.rsfinaltask.feature.data

import android.os.Parcel
import android.os.Parcelable

data class NewsItem(
    val title: String?,
    val link: String?,
    val description: String?,
    val imageUrl: String?,
    val pubDate: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(link)
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
