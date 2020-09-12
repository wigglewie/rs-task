package com.gmail.wigglewie.rsfinaltask.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gmail.wigglewie.rsfinaltask.R
import com.gmail.wigglewie.rsfinaltask.feature.data.NewsItem
import kotlinx.android.synthetic.main.rv_item.view.rv_item_image
import kotlinx.android.synthetic.main.rv_item.view.rv_item_text_date
import kotlinx.android.synthetic.main.rv_item.view.rv_item_text_title

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
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items[position]
        holder.textTitle.text = item.title
        holder.textPubDate.text = item.pubDate

        Glide.with(holder.image.context)
            .load(item.imageUrl)
            .thumbnail(Glide.with(holder.image.context).load(R.drawable.loader))
            .into(holder.image)

        holder.itemView.setOnClickListener { listener?.invoke(item) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTitle: TextView = itemView.rv_item_text_title
        val textPubDate: TextView = itemView.rv_item_text_date
        val image: ImageView = itemView.rv_item_image
    }
}
