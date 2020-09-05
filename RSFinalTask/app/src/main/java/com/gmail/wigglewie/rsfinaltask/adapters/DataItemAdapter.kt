package com.gmail.wigglewie.rsfinaltask.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.gmail.wigglewie.rsfinaltask.R
import com.gmail.wigglewie.rsfinaltask.feature.data.NewsItem
import kotlinx.android.synthetic.main.rv_item.view.rv_item_image
import kotlinx.android.synthetic.main.rv_item.view.rv_item_loader
import kotlinx.android.synthetic.main.rv_item.view.rv_item_text_date
import kotlinx.android.synthetic.main.rv_item.view.rv_item_text_title
import pl.droidsonroids.gif.GifImageView

class DataItemAdapter() :
    RecyclerView.Adapter<DataItemAdapter.ViewHolder>() {

    private var items = mutableListOf<NewsItem>()
    private var listener: ((NewsItem) -> Unit)? = null

    constructor(
        notesList: List<NewsItem>,
        listener: (NewsItem) -> Unit
    ) : this() {
        items = notesList.toMutableList()
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

//        holder.textSpeaker.setTextColor(colorSpeaker)
//        holder.textTitle.setTextColor(colorTitle)

        val item = items[position]
        holder.textTitle.text = item.title
        holder.textPubDate.text = item.pubDate

        Glide.with(holder.image.context)
            .load(item.imageUrl)
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
                    holder.loader.visibility = View.GONE
                    return false
                }
            })
            .into(holder.image)

        println()

        holder.itemView.setOnClickListener { listener?.invoke(item) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTitle: TextView = itemView.rv_item_text_title
        val textPubDate: TextView = itemView.rv_item_text_date
        val image: ImageView = itemView.rv_item_image
        val loader: GifImageView = itemView.rv_item_loader
    }
}
