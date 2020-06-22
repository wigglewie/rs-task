package com.gmail.wigglewie.rs_task4

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.ListAdapter
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val ADD_ITEM_REQUEST_CODE = 0

//    private lateinit var linearLayoutManager: LinearLayoutManager

    private var mDogs = arrayListOf<Dog>(
        Dog("Fedya", 2, "Sdd"),
        Dog("Misha", 15, "Dss"),
        Dog("Denis", 25, "Assd"),
        Dog("Iliya", 18, "Wdd"),
        Dog("Bomj", 21, "Ysad"),
        Dog("Petuh", 25, "Saaass"),
        Dog("Grove", 5, "Jasd"),
        Dog("Knowledge", 55, "Fyt"),
        Dog("Grove", 5, "Jasd"),
        Dog("Grove", 5, "Jasd"),
        Dog("Grove", 5, "Jasd"),
        Dog("Grove", 5, "Jasd"),
        Dog("Grove", 5, "Jasd"),
        Dog("Grove", 5, "Jasd"),
        Dog("Grove", 5, "Jasd"),
        Dog("Grove", 5, "Jasd"),
        Dog("Grove", 5, "Jasd"),
        Dog("Grove", 5, "Jasd"),
        Dog("Grove", 5, "Jasd"),
        Dog("Grove", 5, "Jasd"),
        Dog("Grove", 5, "Jasd")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        recyclerView.adapter = DogAdapter(mDogs)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        recyclerView.setHasFixedSize(true)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->

            val intent = Intent(this, AddItemActivity::class.java)
            startActivityForResult(intent, ADD_ITEM_REQUEST_CODE)

        }

//        linearLayoutManager = LinearLayoutManager(this)
//        recyclerView.layoutManager = linearLayoutManager
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_filter -> {
                Toast.makeText(this, "Action Filter", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_ITEM_REQUEST_CODE) {

            if (resultCode == Activity.RESULT_OK) {

                val stringName = data!!.getStringExtra("name")
                val stringAge = data.getStringExtra("age")
                val stringBreed = data.getStringExtra("breed")

                if (stringName.isEmpty() || stringAge.isEmpty() || stringBreed.isEmpty()) {
                    
                } else {
                    mDogs.add(Dog(stringName, stringAge.toInt(), stringBreed))
                }



                println()
            }
        }

    }
}