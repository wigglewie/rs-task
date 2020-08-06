package com.gmail.wigglewie.rstask6.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.wigglewie.rstask6.R
import com.gmail.wigglewie.rstask6.adapters.DataItemAdapter
import com.gmail.wigglewie.rstask6.contract.MainActivityContract
import com.gmail.wigglewie.rstask6.data.DataItem
import com.gmail.wigglewie.rstask6.presenter.MainActivityPresenter
import kotlinx.android.synthetic.main.activity_main.recyclerView

class MainActivity : AppCompatActivity(), MainActivityContract.View {

    private var presenter: MainActivityPresenter? = null
    private var items = mutableListOf<DataItem>()
    private var hasInternetConnection = false
    private var isNightModeEnabled = false

    private var dayModeSpeakerColor = 0
    private var dayModeTitleColor = 0
    private var nightModeSpeakerColor = 0
    private var nightModeTitleColor = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "All video"

        val systemService = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = systemService.activeNetwork
        if (activeNetwork == null) {
            hasInternetConnection = false
            Toast.makeText(this, "Loaded from JSON \n Some data can be missing", Toast.LENGTH_LONG)
                .show()
        } else {
//            for xml load
            hasInternetConnection = true
            Toast.makeText(this, "Connected", Toast.LENGTH_SHORT)
                .show()
        }

        val inputStream = resources.openRawResource(R.raw.data)
        presenter = MainActivityPresenter(this, inputStream, hasInternetConnection)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_day_night_mode -> {
                presenter?.switchDayNightMode(isNightModeEnabled)!!
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun initView(dataItems: MutableList<DataItem>, mode: Boolean) {
        items = dataItems
        recyclerView.layoutManager = LinearLayoutManager(this)

        dayModeSpeakerColor = resources.getColor(R.color.dayModeColorSpeaker)
        dayModeTitleColor = resources.getColor(R.color.dayModeColorTitle)
        nightModeSpeakerColor = resources.getColor(R.color.nightModeColorSpeaker)
        nightModeTitleColor = resources.getColor(R.color.nightModeColorTitle)

        if (mode) {
            recyclerView.adapter =
                DataItemAdapter(items, nightModeSpeakerColor, nightModeTitleColor) { item ->
                    presenter?.onItemWasClicked(item)
                }
        } else {
            recyclerView.adapter =
                DataItemAdapter(items, dayModeSpeakerColor, dayModeTitleColor) { item ->
                    presenter?.onItemWasClicked(item)
                }
        }
    }

    override fun itemWasClicked(item: DataItem) {
        val intent = Intent(this, ItemViewActivity::class.java)
        intent.putExtra("item", item)
        intent.putExtra("mode", isNightModeEnabled)
        startActivity(intent)
    }

    override fun enableNightMode() {
        recyclerView.setBackgroundResource(R.color.nightModeColorBackground)
        isNightModeEnabled = true
        initView(items, isNightModeEnabled)
    }

    override fun enableDayMode() {
        recyclerView.setBackgroundResource(R.color.dayModeColorBackground)
        isNightModeEnabled = false
        initView(items, isNightModeEnabled)
    }
}
