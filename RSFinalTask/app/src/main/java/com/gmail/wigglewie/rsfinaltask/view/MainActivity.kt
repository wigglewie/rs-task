package com.gmail.wigglewie.rsfinaltask.view

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.Parcelable
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.wigglewie.rsfinaltask.R
import com.gmail.wigglewie.rsfinaltask.adapters.DataItemAdapter
import com.gmail.wigglewie.rsfinaltask.feature.MainViewModel
import com.gmail.wigglewie.rsfinaltask.feature.data.NewsItem
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.activity_main_loader
import kotlinx.android.synthetic.main.activity_main.recyclerView

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    private var isLoading: Boolean = true

    private val viewModel by viewModels<MainViewModel>()
    private var topic = R.string.topic_top_stories

    private var news = mutableListOf<NewsItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar_main)

        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)

        toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        if (savedInstanceState != null) {
            topic = savedInstanceState.getInt("TOPIC")
            recyclerView.layoutManager?.onRestoreInstanceState(savedInstanceState)
            val bundle = savedInstanceState.getBundle("DATA")
            val parcelableArrayList = bundle?.getParcelableArrayList<NewsItem>("LIST")
            val toMutableList = parcelableArrayList?.toMutableList()
            if (toMutableList != null) {
                news = toMutableList
            }
            if (news.size == 0) {
                loadData(topic)
            }
            showData(news)
        } else {
            loadData(topic)
        }
        supportActionBar?.setTitle(topic)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("TOPIC", topic)
        outState.putParcelable(
            "LAYOUT_MANAGER_STATE",
            recyclerView.layoutManager?.onSaveInstanceState()
        )
        val bundle = Bundle().apply {
            putParcelableArrayList("LIST", ArrayList<Parcelable>(news))
        }
        outState.putBundle("DATA", bundle)
    }

    private fun loadData(topic: Int) {
        supportActionBar?.setTitle(topic)
        isLoading = true
        viewModel.getNetworkData(topic) { items, result ->
            if (result != "error") {
                if (items != null) {
                    news = items
                }
                isLoading = false
                activity_main_loader.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                showData(news)
            } else {
                activity_main_loader.visibility = View.VISIBLE
                showErrorAtLoading()
            }
        }
    }

    private fun showData(items: MutableList<NewsItem>) {
        activity_main_loader.visibility = View.GONE
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        val dataItemAdapter = DataItemAdapter(items) { item ->
            val intent = Intent(this, ItemViewActivity::class.java)
            intent.putExtra("item", item)
            intent.putExtra("topic", topic)
            startActivity(intent)
        }
        recyclerView.adapter = dataItemAdapter
    }

    private fun showErrorAtLoading() {
        recyclerView.visibility = View.GONE
        // TODO error message
//        rv_item_loader.visibility = View.GONE
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.menu_main_activity_action_switch_mode -> {
                println()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.nav_item_top_stories -> {
                topic = R.string.topic_top_stories
                loadData(topic)
            }

            R.id.nav_item_middle_east -> {
                topic = R.string.topic_middle_east
                loadData(topic)
            }

            R.id.nav_item_africa -> {
                topic = R.string.topic_africa
                loadData(topic)
            }
            R.id.nav_item_europe -> {
                topic = R.string.topic_europe
                loadData(topic)
            }
            R.id.nav_item_americas -> {
                topic = R.string.topic_americas
                loadData(topic)
            }
            R.id.nav_item_asia_pacific -> {
                topic = R.string.topic_asia_pacific
                loadData(topic)
            }

            R.id.nav_item_health -> {
                topic = R.string.topic_health
                loadData(topic)
            }
            R.id.nav_item_un_affairs -> {
                topic = R.string.topic_un_affairs
                loadData(topic)
            }
            R.id.nav_item_law_and_crime_prevention -> {
                topic = R.string.topic_law_and_crime_prevention
                loadData(topic)
            }
            R.id.nav_item_human_rights -> {
                topic = R.string.topic_human_rights
                loadData(topic)
            }
            R.id.nav_item_humanitarian_aid -> {
                topic = R.string.topic_humanitarian_aid
                loadData(topic)
            }
            R.id.nav_item_climate_change -> {
                topic = R.string.topic_climate_change
                loadData(topic)
            }
            R.id.nav_item_culture_and_education -> {
                topic = R.string.topic_culture_and_education
                loadData(topic)
            }
            R.id.nav_item_economic_development -> {
                topic = R.string.topic_economic_development
                loadData(topic)
            }
            R.id.nav_item_women -> {
                topic = R.string.topic_women
                loadData(topic)
            }
            R.id.nav_item_peace_and_security -> {
                topic = R.string.topic_peace_and_security
                loadData(topic)
            }
            R.id.nav_item_migrants_and_refugees -> {
                topic = R.string.topic_migrants_and_refugees
                loadData(topic)
            }
            R.id.nav_item_sdgs -> {
                topic = R.string.topic_sdgs
                loadData(topic)
            }
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
