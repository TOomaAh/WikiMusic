package com.example.wikimusic.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wikimusic.R
import com.example.wikimusic.models.Album
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_album_cell.view.*
import org.w3c.dom.Text
import javax.security.auth.callback.Callback

class ItemListAdapter<T>(private val items: List<T>) : RecyclerView.Adapter<ItemListViewHolder<T>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder<T> {
        if (items.isNotEmpty() && items[0] is Album){
            return ItemListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_album_cell,
                parent,
                false))
        }
        return ItemListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_artist_cell,
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

    //Album
//    val thumbnail : ImageView = v.itemAlbumIcon
//    val albumName: TextView = v.itemNameText
//    val artistName: TextView = v.artist_name_album

    //Artist
//    val thumbnail: ImageView = v.itemAlbumIcon
//    val artistName: TextView = v.itemNameText

    fun updateItem(item: T){
        //Album
            //albumName.text = item.strAlbum
            //artistName.text = item.strArtist
//        Picasso.get().load(item.strAlbumThumb).into(thumbnail, object : Callback {
//            override fun onSuccess() {
//
//            }
//
//            override fun onError(e: Exception?) {
//
//            }
//        })

        //Artist
            //artistName.text = item.strArtist
//        Picasso.get().load(item.strArtistThumb).into(thumbnail, object : Callback {
//            override fun onSuccess() {
//
//            }
//
//            override fun onError(e: Exception?) {
//
//            }
//        })

    }
}