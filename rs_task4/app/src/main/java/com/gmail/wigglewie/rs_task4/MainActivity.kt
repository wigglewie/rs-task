package com.gmail.wigglewie.rs_task4

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.wigglewie.rs_task4.adapters.DogAdapter
import com.gmail.wigglewie.rs_task4.settings.SettingsActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val ADD_ITEM_REQUEST_CODE = 0

    private var mDogs = arrayListOf(
        Dog("Fedya", 25, "Sdd"),
        Dog("Misha", 15, "Dss"),
        Dog("Denis", 25, "Assd"),
        Dog("Iliya", 18, "Wdd"),
        Dog("Bomj", 21, "Ysad"),
        Dog("Petuh", 25, "Saaass"),
        Dog("Grove", 5, "Jasd"),
        Dog("Knowledge", 55, "Fyt"),
        Dog("Grove", 52, "Jasd"),
        Dog("Grove", 25, "Jasd"),
        Dog("Grove", 57, "Jasd"),
        Dog("Grove", 58, "Jasd"),
        Dog("Grove", 59, "Jasd"),
        Dog("Grove", 95, "Jasd"),
        Dog("Grove", 58, "Jasd"),
        Dog("Grove", 52, "Jasd"),
        Dog("Grove", 51, "Jasd"),
        Dog("Grove", 532, "Jasd"),
        Dog("Grove", 15, "Jasd"),
        Dog("Grove", 53213, "Jasd"),
        Dog("Grove", 1, "Jasd")

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        recyclerView.adapter =
            DogAdapter(mDogs)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        recyclerView.setHasFixedSize(true)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {

            val intent = Intent(this, AddItemActivity::class.java)
            startActivityForResult(intent, ADD_ITEM_REQUEST_CODE)
        }
        println()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        when (prefs.getString("list_preference", "1").toString()) {
            "Name" -> {
                mDogs.sortBy { it.name }
            }
            "Age" -> {
                mDogs.sortBy { it.age }
            }
            "Breed" -> {
                mDogs.sortBy { it.breed }
            }
        }
        recyclerView.adapter?.notifyDataSetChanged()
        super.onResume()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_ITEM_REQUEST_CODE) {

            if (resultCode == Activity.RESULT_OK) {

                val stringName = data!!.getStringExtra("name")
                val stringAge = data.getStringExtra("age")
                val stringBreed = data.getStringExtra("breed")

                if (stringName!!.isEmpty() || stringAge!!.isEmpty() || stringBreed!!.isEmpty()) {
                    // TODO: 23-JUNE-2020 EDIT IF SECTION
                } else {
                    mDogs.add(Dog(stringName, stringAge.toInt(), stringBreed))
                }

                println()
            }
        }

    }
}