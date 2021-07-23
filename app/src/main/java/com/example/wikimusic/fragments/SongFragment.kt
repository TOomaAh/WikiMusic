package com.example.wikimusic.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.wikimusic.R
import com.example.wikimusic.models.Track
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_album.view.*
import kotlinx.android.synthetic.main.fragment_artist.view.*
import kotlinx.android.synthetic.main.fragment_song.view.*
import kotlinx.android.synthetic.main.fragment_song.view.background_img

class SongFragment : Fragment() {

    val args: SongFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_song, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.left_arrow_song.setOnClickListener {
            findNavController().popBackStack()
        }

        val song : Track = args.track
        view.song_lyrics.text = "Api morche po"
        view.song_name.text = song.strTrack
        Picasso.get().load(song.strTrackThumb).into(view.song_img)
        Picasso.get().load(song.strTrackThumb).into(view.background_img)

    }
}