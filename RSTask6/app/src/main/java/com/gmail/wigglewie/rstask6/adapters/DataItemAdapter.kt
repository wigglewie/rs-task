package com.gmail.wigglewie.rstask6.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gmail.wigglewie.rstask6.R
import com.gmail.wigglewie.rstask6.data.DataItem
import kotlinx.android.synthetic.main.rv_item.view.rv_imageVideo
import kotlinx.android.synthetic.main.rv_item.view.rv_textSpeaker
import kotlinx.android.synthetic.main.rv_item.view.rv_textTitle
import kotlinx.android.synthetic.main.rv_item.view.rv_textVideoDuration

class DataItemAdapter() :
    RecyclerView.Adapter<DataItemAdapter.ViewHolder>() {

    private var items = mutableListOf<DataItem>()
    private var listener: ((DataItem) -> Unit)? = null
    private var mode: Boolean = true

    private var colorSpeaker = 0
    private var colorTitle = 0

    constructor(
        notesList: List<DataItem>,
        _colorSpeaker: Int,
        _colorTitle: Int,
        _listener: (DataItem) -> Unit
    ) : this() {
        items = notesList.toMutableList()
        colorSpeaker = _colorSpeaker
        colorTitle = _colorTitle
        listener = _listener
    }

    constructor(notesList: List<DataItem>, _colorSpeaker: Int, _colorTitle: Int) : this() {
        items = notesList.toMutableList()
        colorSpeaker = _colorSpeaker
        colorTitle = _colorTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.textSpeaker.setTextColor(colorSpeaker)
        holder.textTitle.setTextColor(colorTitle)

//        if (mode) {
//            //night mode
//            holder.textSpeaker.setTextColor(colorSpeaker)
//            holder.textTitle.setTextColor(colorTitle)
//        } else {
//            //day mode
//            holder.textSpeaker.setTextColor(Color.BLUE)
//            holder.textTitle.setTextColor(Color.BLUE)
//        }

        val item = items[position]
        holder.textTitle.text = item.title
        holder.textSpeaker.text = item.speaker
        holder.videoDuration.text = item.videoDuration

        Glide.with(holder.imageVideo.context)
            .load(item.imageUrl)
            .into(holder.imageVideo)

        holder.itemView.setOnClickListener { listener?.invoke(item) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTitle: TextView = itemView.rv_textTitle
        val textSpeaker: TextView = itemView.rv_textSpeaker
        val imageVideo: ImageView = itemView.rv_imageVideo
        val videoDuration: TextView = itemView.rv_textVideoDuration
    }

}
