package com.gmail.wigglewie.rsfinaltask.view

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.gmail.wigglewie.rsfinaltask.R
import com.gmail.wigglewie.rsfinaltask.feature.data.NewsItem
import kotlinx.android.synthetic.main.activity_view_news_item.item_view_button_share
import kotlinx.android.synthetic.main.activity_view_news_item.item_view_button_visit_website
import kotlinx.android.synthetic.main.activity_view_news_item.item_view_image
import kotlinx.android.synthetic.main.activity_view_news_item.item_view_loader
import kotlinx.android.synthetic.main.activity_view_news_item.item_view_scrollView
import kotlinx.android.synthetic.main.activity_view_news_item.item_view_text_date
import kotlinx.android.synthetic.main.activity_view_news_item.item_view_text_description
import kotlinx.android.synthetic.main.activity_view_news_item.item_view_text_title
import kotlinx.android.synthetic.main.activity_view_news_item.item_view_text_topic
import kotlinx.android.synthetic.main.activity_view_news_item.toolbar_item_view

class ItemViewActivity : AppCompatActivity() {

    private var newsItem: NewsItem? = null

    companion object {
        const val RESULT_ADD_TO_FAVORITES = 0
        const val RESULT_REMOVE_FROM_FAVORITES = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_news_item)

        setSupportActionBar(toolbar_item_view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        newsItem = intent.getParcelableExtra("ITEM") as NewsItem
        val stringExtra = intent.getIntExtra("TOPIC", R.string.topic_top_stories)

        supportActionBar?.title = resources.getText(stringExtra)

        Glide.with(this)
            .load(newsItem?.imageUrl)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    item_view_loader.visibility = View.GONE
                    return false
                }
            })
            .into(item_view_image)

        item_view_text_topic.text = resources.getText(stringExtra)
        item_view_text_date.text = newsItem?.pubDate
        item_view_text_title.text = newsItem?.title
        item_view_text_description.text = newsItem?.description

        item_view_button_share.setOnClickListener {
            Toast.makeText(this, "SHARE", Toast.LENGTH_SHORT).show()
        }
        item_view_button_visit_website.setOnClickListener {
            Toast.makeText(this, "VISIT WEBSITE", Toast.LENGTH_SHORT).show()
        }

        item_view_button_share.setOnClickListener {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.putExtra(Intent.EXTRA_TEXT, newsItem?.link)
            shareIntent.type = "text/plain"
            Intent.createChooser(shareIntent, "Share via")
            startActivity(shareIntent)
        }

        item_view_button_visit_website.setOnClickListener {
            val builder = CustomTabsIntent.Builder()
            builder.setToolbarColor(ContextCompat.getColor(this, R.color.colorToolbar))
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(this, Uri.parse(newsItem?.link))
        }

        if (savedInstanceState != null) {
            val position = savedInstanceState.getIntArray("SCROLLVIEW_STATE")
            if (position != null) {
                item_view_scrollView.post {
                    item_view_scrollView.scrollTo(
                        position[0],
                        position[1]
                    )
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_add_to_favorites -> {
                setResult(RESULT_ADD_TO_FAVORITES, intent)
                true
            }
            R.id.action_remove_from_favorites -> {
                setResult(RESULT_REMOVE_FROM_FAVORITES, intent)
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item_view_activity, menu)
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val valueX = item_view_scrollView.scrollX
        val valueY = item_view_scrollView.scrollY
        val array = intArrayOf(valueX, valueY)
        outState.putIntArray("SCROLLVIEW_STATE", array)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
