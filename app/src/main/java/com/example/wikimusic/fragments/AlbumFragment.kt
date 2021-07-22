package com.example.wikimusic.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.wikimusic.R
import com.example.wikimusic.models.Album
import com.example.wikimusic.services.ApiClient
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_album.*
import kotlinx.android.synthetic.main.fragment_album.view.*
import kotlinx.android.synthetic.main.fragment_album.view.background_img
import kotlinx.android.synthetic.main.fragment_album.view.recyclerTracksDetails
import kotlinx.android.synthetic.main.fragment_artist.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class AlbumFragment : Fragment() {

    val args: AlbumFragmentArgs by navArgs()

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
        val album: Album = args.album

        GlobalScope.launch {
            withContext(Dispatchers.Main){
                Picasso.get().load(album.strAlbumThumb).into(view.background_img)
                Picasso.get().load(album.strAlbumThumb).into(view.album_img)
                view.artistTitle.text = album.strArtist
                view.album_name.text = album.strAlbum
                view.average_vote.text = album.intScore
                view.vote_number.text = String.format("%s votes", album.intScoreVotes)
                view.description_album.text = if (album.strDescriptionFR != null && Locale.getDefault().displayLanguage == "fr_FR") album.strDescriptionFR else album.strDescriptionEN
            }

        }
        //hide bottom bar

        val recyclerTracks : RecyclerView = view.recyclerTracksDetails //Cell Layout = R.layout.item_track_cell_details
    }
}