package com.gmail.wigglewie.rs_task4

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.wigglewie.rs_task4.adapters.DogAdapter
import com.gmail.wigglewie.rs_task4.database.dao.Dog
import com.gmail.wigglewie.rs_task4.database.dao.TableDao
import com.gmail.wigglewie.rs_task4.settings.SettingsActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val ADD_ITEM_REQUEST_CODE = 0

    private val dao = TableDao()
    private var mDogs = mutableListOf<Dog>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)

        if (prefs.getBoolean("firstrun", true)) {
            createDogsTable(dao)
            prefs.edit().putBoolean("firstrun", false).apply()
        }

        mDogs = dao.queryForAll()
        recyclerView.adapter = DogAdapter(mDogs)
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
        when (prefs.getString("preference_settings_sort", "1").toString()) {
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

                val stringName = data?.getStringExtra("name").toString()
                val stringAge = data?.getStringExtra("age").toString()
                val stringBreed = data?.getStringExtra("breed").toString()

                dao.add(Dog(null, stringName, stringAge.toInt(), stringBreed))
                mDogs.add(Dog(null, stringName, stringAge.toInt(), stringBreed))
            }
        }
    }

    private fun createDogsTable(dao: TableDao) {
        dao.add(Dog(null, "Max", 8, "German Shepherd Dog"))
        dao.add(Dog(null, "Teddy", 7, "American Bulldog"))
        dao.add(Dog(null, "Ollie", 5, "Mastiff"))
        dao.add(Dog(null, "Buddy", 3, "Beagle"))
        dao.add(Dog(null, "Ruby", 3, "Chihuahua"))
        dao.add(Dog(null, "Lola", 12, "Borzoi"))
        dao.add(Dog(null, "Archie", 9, "Bulldog"))
    }

}