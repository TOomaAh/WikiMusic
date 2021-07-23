package com.example.wikimusic.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wikimusic.R
import com.example.wikimusic.adapters.ItemListAdapter
import com.example.wikimusic.models.Album
import com.example.wikimusic.models.Track
import com.example.wikimusic.services.ApiClient
import com.google.android.material.bottomnavigation.BottomNavigationView
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
        val navbar: BottomNavigationView = requireActivity().findViewById(R.id.home_bottom_nav)
        navbar.visibility = View.GONE
        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.left_arrow.setOnClickListener {
            findNavController().popBackStack()
        }

        val album: Album = args.album

        GlobalScope.launch {
            withContext(Dispatchers.Main){
                val responseTracks = ApiClient.apiService.getTracksByAlbum(album.idAlbum.toString())
                val bodyTracks = responseTracks.body()
                if (bodyTracks != null) {
                    withContext(Dispatchers.Main){
                        view.recyclerTracksDetails.layoutManager = LinearLayoutManager(requireContext())
                        if (bodyTracks.track != null){
                            val tracks: List<Track> = responseTracks.body()!!.track
                            view.subTextAlbum.text = String.format("%s %s", tracks.count(), getString(R.string.songs))
                            view.recyclerTracksDetails.adapter = ItemListAdapter<Track>(tracks, requireContext()){

                            }
                        }
                    }
                }
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
    }
}