package com.example.wikimusic.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wikimusic.R
import com.example.wikimusic.adapters.ItemListAdapter
import com.example.wikimusic.models.Album
import com.example.wikimusic.models.Artist
import com.example.wikimusic.models.Track
import com.example.wikimusic.services.ApiClient
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_artist.*
import kotlinx.android.synthetic.main.fragment_artist.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class ArtistFragment : Fragment() {

    //val artist: Artist = Artist()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_artist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        GlobalScope.launch {
//                    val responseAlbum = ApiClient.apiService.getAllalbumByArtist(artist.strArtist)
//                    val responseTracks = ApiClient.apiService.getTopTracks(artist.strArtist)
//                    withContext(Dispatchers.Main) {
//                        Picasso.get().load(artist.strArtistWideThumb).into(view.background_img)
//                        view.artistName.text = artist.strArtist
//                        view.subTextArtist.text = String.format("%s %s", artist.strCountry, artist.strGenre)
//
//
//
//                        view.description_artist.text =
//                            if (artist.strBiographyFR != null && Locale.getDefault().displayLanguage == "fr_FR") artist.strBiographyFR else artist.strBiographyEN
//
//                        if (responseAlbum.body() != null){
//                            val albumBody = responseAlbum.body()!!
//                            if (albumBody.album != null){
//                                view.recyclerRecordDetails.layoutManager = LinearLayoutManager(requireContext())
//                                view.recyclerRecordDetails.adapter = ItemListAdapter<Album>(albumBody.album, requireContext())
//                            }
//                        }
//
//                        if (responseTracks.body() != null){
//                            val tracksBody = responseTracks.body()!!
//                            if (tracksBody.track != null){
//                                view.recyclerTracksDetails.layoutManager = LinearLayoutManager(requireContext())
//                                view.recyclerTracksDetails.adapter = ItemListAdapter<Track>(tracksBody.track, requireContext())
//                            }
//                        }
//                        //cell Layout = R.id.item_album_cell.xml
//                        //comme pour la liste recherche sauf : artist_name_album = année de sortie album
//
//                        //cell Layout = R.id.item_track_cell_details.xml
//                        //cell.song_number = classement de la chanson
//                        //cell.song_title = titre de la chanson
//            }
//        }
    }
}