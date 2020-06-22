package com.gmail.wigglewie.rs_task4

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)
//        setSupportActionBar(findViewById(R.id.toolbar_add_item))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        findViewById<Button>(R.id.button_add_item).setOnClickListener { _ ->

            val textName = findViewById<EditText>(R.id.editText_name).text.toString()
            val textAge = findViewById<EditText>(R.id.editText_age).text.toString()
            val textBreed = findViewById<EditText>(R.id.editText_breed).text.toString()

            val intent = Intent()
            intent.putExtra("name", textName)
            intent.putExtra("age", textAge)
            intent.putExtra("breed", textBreed)
            setResult(Activity.RESULT_OK, intent)

            if (textName.isEmpty() || textAge.isEmpty() || textBreed.isEmpty()) {
                when {
                    textName.isEmpty() -> {
                        Toast.makeText(this, "Enter name", Toast.LENGTH_SHORT).show()
                    }
                    textAge.isEmpty() -> {
                        Toast.makeText(this, "Enter age", Toast.LENGTH_SHORT).show()
                    }
                    textBreed.isEmpty() -> {
                        Toast.makeText(this, "Enter breed", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                finish()
            }
        }

    }
}