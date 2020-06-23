package com.gmail.wigglewie.rs_task4.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gmail.wigglewie.rs_task4.Dog
import com.gmail.wigglewie.rs_task4.R
import kotlinx.android.synthetic.main.rv_item.view.*

class DogAdapter(private val dogList: ArrayList<Dog>) :
    RecyclerView.Adapter<DogAdapter.DogViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.rv_item, parent, false
        )

        return DogViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val currentItem = dogList[position]

        holder.textName.text = currentItem.name
        holder.textAge.text = currentItem.age.toString()
        holder.textBreed.text = currentItem.breed
    }

    override fun getItemCount() = dogList.size

    class DogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textName: TextView = itemView.itemTextViewNameData
        val textAge: TextView = itemView.itemTextViewAgeData
        val textBreed: TextView = itemView.itemTextViewBreedData
    }


}