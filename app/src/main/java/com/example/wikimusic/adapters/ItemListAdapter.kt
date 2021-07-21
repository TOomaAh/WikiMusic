package com.example.wikimusic.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wikimusic.R
import com.example.wikimusic.models.Album

class ItemListAdapter<T>(private val items: List<T>) : RecyclerView.Adapter<ItemListViewHolder<T>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder<T> {
        if (items.isNotEmpty() && items[0] is Album){
            return ItemListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_album_cell,
                parent,
                false))
        }
        return ItemListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_album_cell,
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ItemListViewHolder<T>, position: Int) {
        holder.updateItem(items[position]);
    }

    override fun getItemCount(): Int {
        return items.size;
    }
}


class ItemListViewHolder<T>(v: View) : RecyclerView.ViewHolder(v){
    fun updateItem(items: T){

    }
}