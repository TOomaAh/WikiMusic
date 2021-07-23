package com.example.wikimusic.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.wikimusic.R
import com.example.wikimusic.models.Track
import kotlinx.android.synthetic.main.fragment_song.view.*

class SongFragment : Fragment() {

    val args: ArtistFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_song, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val song : Track = args.track
        //view.song_lyrics = song.lyrics --> marche po
        //view.song_name = track.strTrack
        //view.album_img = track.strTrackThumb
        //view.background_img = track.strTrackThumb

    }
}