package com.example.wikimusic.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.wikimusic.R
import com.example.wikimusic.fragments.ArtistFragment
import com.example.wikimusic.fragments.SearchFragment
import com.example.wikimusic.fragments.SearchFragmentDirections
import com.example.wikimusic.models.Album
import com.example.wikimusic.models.Artist
import com.example.wikimusic.models.Track
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_artist.view.*
import kotlinx.android.synthetic.main.item_album_cell.view.*
import kotlinx.android.synthetic.main.item_track_cell_details.view.*
import org.w3c.dom.Text
import java.lang.IllegalArgumentException
import javax.security.auth.callback.Callback

class ItemListAdapter<T>(private val items: List<Any>, private val context: Context, private val listener: (T) -> Unit) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    companion object {
        private const val TYPE_ALBUM = 0
        private const val TYPE_ARTIST = 1
        private const val TYPE_TRACK = 2
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
            TYPE_TRACK -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_track_cell_details, parent, false)
                TrackViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid Argument")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val element = items[position]
        when (holder) {
            is AlbumViewHolder -> holder.bind(element as Album, listener as (Album) -> Unit)
            is ArtistViewHolder -> holder.bind(element as Artist, listener as (Artist) -> Unit)
            is TrackViewHolder -> holder.bind(element as Track, listener as (Track) -> Unit, position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is Album -> TYPE_ALBUM
            is Artist -> TYPE_ARTIST
            is Track -> TYPE_TRACK
            else -> throw IllegalArgumentException("Invalid type")
        }
    }

}

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T, listener: (T) -> Unit)
    abstract fun bind(item: T, listener: (T) -> Unit,position: Int)
}

class AlbumViewHolder(v: View): BaseViewHolder<Album>(v){

    private val thumbnail : ImageView = v.itemAlbumIcon
    private val albumName: TextView = v.itemNameText
    private val artistName: TextView = v.artist_name_album
    private val view: View = v

    override fun bind(item: Album, listener: (Album) -> Unit) {
        albumName.text = item.strAlbum
        artistName.text = item.strArtist
        if (item.strAlbumThumb != null && item.strAlbumThumb.isNotEmpty()){
            Picasso.get().load(item.strAlbumThumb).into(thumbnail)
        }
        view.setOnClickListener{
            listener(item)
        }

    }

    override fun bind(item: Album, listener: (Album) -> Unit, position: Int) {}
}

class ArtistViewHolder(v: View) : BaseViewHolder<Artist>(v){
    private val thumbnail: ImageView = v.itemAlbumIcon
    private val artistName: TextView = v.itemNameText
    private val view: View = v


    override fun bind(item: Artist, listener: (Artist) -> Unit) {
        artistName.text = item.strArtist
        if (item.strArtistThumb != null && item.strArtistThumb.isNotEmpty()){
            Picasso.get().load(item.strArtistThumb)
                .into(thumbnail)
        }

        view.setOnClickListener{
            listener(item)
        }
    }

    override fun bind(item: Artist, listener: (Artist) -> Unit, position: Int) {}
}

class TrackViewHolder(v: View) : BaseViewHolder<Track>(v){

    private val trackName: TextView = v.song_title
    private val trackNumber: TextView = v.song_number

    override fun bind(item: Track, listener: (Track) -> Unit) {}

    override fun bind(item: Track, listener: (Track) -> kotlin.Unit, position: Int) {
        trackName.text = item.strTrack
        trackNumber.text = position.toString()

    }

}
