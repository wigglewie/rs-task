package com.gmail.wigglewie.rsfinaltask.feature.data.database

import android.app.Application
import androidx.room.Room
import javax.inject.Inject

class LocalData @Inject constructor() {

    fun getLocalData(
        application: Application
    ): NewsItemDataBase {

        val db = Room.databaseBuilder(
            application.applicationContext,
            NewsItemDataBase::class.java, "news_items_favorites"
        )
            .build()
        println()
        return db
    }
}
