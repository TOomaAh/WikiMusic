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
import androidx.room.Room
import com.example.wikimusic.R
import com.example.wikimusic.adapters.ItemListAdapter
import com.example.wikimusic.entity.FavorisRoomDb
import com.example.wikimusic.models.Album
import com.example.wikimusic.models.Artist
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

        val db = Room.databaseBuilder(
            requireContext(),
            FavorisRoomDb::class.java, "favoris"
        ).allowMainThreadQueries().build()
        val favsDao = db.favDao()

        val favList: List<Album> = favsDao.getAlbum()
        for (i in favList.indices){
            if (album.idAlbum!!.equals(favList.get(i).idAlbum)){
                view.icone_fav.visibility = View.INVISIBLE
                view.icone_fav_liked.visibility = View.VISIBLE
            }
        }

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
                                val action = AlbumFragmentDirections.actionAlbumFragmentToSongFragment(it)
                                findNavController().navigate(action)

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
                view.description_album.text = if (album.strDescriptionFR != null && Locale.getDefault().displayLanguage == "fran??ais") album.strDescriptionFR else album.strDescriptionEN
            }

        }

        view.icone_fav.setOnClickListener{
            val db = Room.databaseBuilder(
                requireContext(),
                FavorisRoomDb::class.java, "favoris"
            ).allowMainThreadQueries().build()
            val favsDao = db.favDao()
            favsDao.insertAlbum(
                Album(null,album.idAlbum,
                    album.strAlbum,
                    album.strArtist,
                    album.intYearReleased,
                    album.strAlbumThumb,
                    album.strDescriptionEN,
                    album.strDescriptionFR,
                    album.intScore,
                    album.intScoreVotes)
            )
            view.icone_fav_liked.visibility = View.VISIBLE
            view.icone_fav.visibility = View.INVISIBLE
        }

        view.icone_fav_liked.setOnClickListener{
            val db = Room.databaseBuilder(
                requireContext(),
                FavorisRoomDb::class.java, "favoris"
            ).allowMainThreadQueries().build()
            val favsDao = db.favDao()
            favsDao.deleteAlbumFav(
                album.idAlbum!!
            )
            view.icone_fav_liked.visibility = View.INVISIBLE
            view.icone_fav.visibility = View.VISIBLE

        }
        //hide bottom bar
    }
}