package com.example.lists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide

class RecyclerViewAdapter( val items: MutableList<Meme>, val onClick:(String)->Unit) : RecyclerView.Adapter<RecycleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewHolder {
        val listItemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_layout, parent, false)
        return RecycleViewHolder(listItemView)
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
        holder.title.text = items[position].name
        Glide.with(holder.itemView.context)
            .load(items[position].url)
            .into(holder.image)
        holder.itemView.setOnClickListener { onClick(items[position].name) }
    }
}

class RecycleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.listTitle)
    val image: ImageView = itemView.findViewById(R.id.listImage)
}

