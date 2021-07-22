package com.example.wikimusic.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wikimusic.R
import com.example.wikimusic.models.Album
import com.example.wikimusic.models.Artist
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_album_cell.view.*
import org.w3c.dom.Text
import java.lang.IllegalArgumentException
import javax.security.auth.callback.Callback

class ItemListAdapter<T>(private val items: List<Any>, private val context: Context) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    companion object {
        private const val TYPE_ALBUM = 0
        private const val TYPE_ARTIST = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            TYPE_ALBUM -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_album_cell, parent, false)
                AlbumViewHolder(view)
            }
            TYPE_ARTIST -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_artist_cell, parent, false)
                ArtistViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid Argument")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val element = items[position]
        when (holder) {
            is AlbumViewHolder -> holder.bind(element as Album)
            is ArtistViewHolder -> holder.bind(element as Artist)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is Album -> TYPE_ALBUM
            is Artist -> TYPE_ARTIST
            else -> throw IllegalArgumentException("Invalid type")
        }
    }

}

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}

class AlbumViewHolder(v: View): BaseViewHolder<Album>(v){

    private val thumbnail : ImageView = v.itemAlbumIcon
    private val albumName: TextView = v.itemNameText
    private val artistName: TextView = v.artist_name_album

    override fun bind(item: Album) {
        albumName.text = item.strAlbum
        artistName.text = item.strArtist

        if (item.strAlbumThumb != null && item.strAlbumThumb.isNotEmpty()){
            Picasso.get().load(item.strAlbumThumb).into(thumbnail)
        }

    }
}

class ArtistViewHolder(v: View) : BaseViewHolder<Artist>(v){
    private val thumbnail: ImageView = v.itemAlbumIcon
    private val artistName: TextView = v.itemNameText

    override fun bind(item: Artist) {
        artistName.text = item.strArtist
        if (item.strArtistThumb != null && item.strArtistThumb.isNotEmpty()){
            Picasso.get().load(item.strArtistThumb).into(thumbnail)
        }
    }
}
