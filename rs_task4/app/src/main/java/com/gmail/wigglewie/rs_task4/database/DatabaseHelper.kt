package com.gmail.wigglewie.rs_task4.database

import android.database.sqlite.SQLiteDatabase
import com.gmail.wigglewie.rs_task4.App
import com.gmail.wigglewie.rs_task4.database.dao.Dog
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils

object DatabaseHelper : OrmLiteSqliteOpenHelper(App.instance, "dog.db", null, 1) {

    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource?) {
        TableUtils.createTableIfNotExists(connectionSource, Dog::class.java)
    }

    override fun onUpgrade(database: SQLiteDatabase?, connectionSource: ConnectionSource?, oldVersion: Int, newVersion: Int) {
        TableUtils.dropTable<Dog, Any>(connectionSource, Dog::class.java, true)
        onCreate(database, connectionSource)
    }

}