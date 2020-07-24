package com.gmail.wigglewie.rstask5.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gmail.wigglewie.rstask5.R
import com.gmail.wigglewie.rstask5.data.Cat
import kotlinx.android.synthetic.main.rv_item.view.rv_item_image

class CatAdapter(
    private val imagesList: List<Cat>,
    private val listener: (Cat) -> Unit
) :
    RecyclerView.Adapter<CatAdapter.CatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return CatViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val currentItem = imagesList[position]

        Glide.with(holder.imageDetailedView.context)
            .load(currentItem.url)
            .into(holder.imageDetailedView)

        holder.itemView.setOnClickListener { listener(currentItem) }
    }

    override fun getItemCount() = imagesList.size

    class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageDetailedView: ImageView = itemView.rv_item_image
    }
}
