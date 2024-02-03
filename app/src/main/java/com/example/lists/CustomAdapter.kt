package com.example.lists

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomAdapter(private val context: Context, private val items: List<ListItem>) :
    BaseAdapter() {
    override fun getCount(): Int = items.count()

    override fun getItem(position: Int): ListItem = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val myConvertView: View
        if (convertView == null) {
            myConvertView =
                LayoutInflater.from(context).inflate(R.layout.list_item_layout, parent, false)
            val title: TextView = myConvertView.findViewById(R.id.listTitle)
            val image: ImageView = myConvertView.findViewById(R.id.listImage)
            val viewHolder = CustomViewHolder(title, image)
            title.text = items[position].title
            myConvertView.tag = viewHolder
        } else {
            myConvertView = convertView
            val convertViewHolder = myConvertView.tag as CustomViewHolder
            convertViewHolder.title.text = items[position].title
        }

        return myConvertView
    }
}

data class ListItem(val imageSrc: String, val title: String)

class CustomViewHolder(val title: TextView, val image: ImageView)