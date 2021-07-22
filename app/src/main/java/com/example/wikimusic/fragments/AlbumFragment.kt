package com.example.wikimusic.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.wikimusic.R
import com.example.wikimusic.services.ApiClient
import kotlinx.android.synthetic.main.fragment_album.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AlbumFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        view.background_img = album_img_big
//        view.artistTitle = artist_name
//        view.album_img = album_img_small
//        view.album_name = album_name
//        view.subTextAlbum = album_song_number_total + "@String/tracks"
//        view.average_vote = album_average_vote
//        view.vote_number = album_nbre_vite_total
//        view.description_album = album_description

        val recyclerTracks : RecyclerView = view.recyclerTracksDetails //Cell Layout = R.layout.item_track_cell_details
    }
}