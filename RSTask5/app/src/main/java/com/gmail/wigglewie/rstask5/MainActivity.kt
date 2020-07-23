package com.gmail.wigglewie.rstask5

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.gmail.wigglewie.rstask5.fragments.ImageListFragment

//var cats = mutableListOf<Cat>()

class MainActivity : AppCompatActivity() {

//    private val catViewModel by viewModels<CatViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        catViewModel.items.observe(ImageListFragment(), Observer {
//            it ?: return@Observer
////            CatAdapter().addItems(it)
//        })

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ImageListFragment())
                .commitAllowingStateLoss()
        }

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                1
            )
        }



//        val retrofit = Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl("https://api.thecatapi.com")
//            .build()
//        val service = retrofit.create(CatService::class.java)
//        val call = service.getCurrentWeatherData()
//        call.enqueue(object : Callback<List<Cat>>{
//
//            override fun onResponse(
//                call: Call<List<Cat>>,
//                response: retrofit2.Response<List<Cat>>
//            ) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onFailure(call: Call<List<Cat>>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//
//        } )

//        cats.add(
//            0,
//            Cat("https://cdn2.thecatapi.com/images/1ul.jpg")
//        )
//        cats.add(
//            1,
//            Cat("https://cdn2.thecatapi.com/images/8g9.jpg")
//        )
//        cats.add(
//            2,
//            Cat("https://cdn2.thecatapi.com/images/8q3.jpg")
//        )
//        cats.add(
//            3,
//            Cat("https://cdn2.thecatapi.com/images/MjA5MDc0MQ.jpg")
//        )

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}


