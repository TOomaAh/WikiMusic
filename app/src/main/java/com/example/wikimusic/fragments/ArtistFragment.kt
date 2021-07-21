package com.example.wikimusic.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.wikimusic.R
import kotlinx.android.synthetic.main.fragment_artist.*
import kotlinx.android.synthetic.main.fragment_artist.view.*

class ArtistFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_artist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        view.background_img = artist_background_img
//        view.artistName = artistName
//        view.subTextArtist = artistHome + artistStyle
//        view.description_artist = artistDescription
        val recyclerRecordArtist : RecyclerView = view.recyclerRecordDetails
        val recyclerTracksArtist : RecyclerView = view.recyclerTracksDetails

    }
}