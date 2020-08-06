package com.gmail.wigglewie.rs_task4.database.dao

import com.gmail.wigglewie.rs_task4.database.DatabaseHelper
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "dog_table")
data class Dog(

    @DatabaseField(generatedId = true)
    var id: Int? = null,

    @DatabaseField
    var name: String = "",

    @DatabaseField
    var age: Int? = null,

    @DatabaseField
    var breed: String = ""
)

class TableDao {

    private val dao = DatabaseHelper.getDao(Dog::class.java)

    fun add(dog: Dog) = dao.createOrUpdate(dog)

    fun update(dog: Dog) = dao.update(dog)

    fun delete(dog: Dog) = dao.delete(dog)

    fun queryForAll() = dao.queryForAll()

    fun removeAll() {
        for (dog in queryForAll()) {
            dao.delete(dog)
        }
    }

}