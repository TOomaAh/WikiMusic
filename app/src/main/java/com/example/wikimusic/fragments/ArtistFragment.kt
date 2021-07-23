package com.example.wikimusic.fragments

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commitNow
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
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
import kotlinx.android.synthetic.main.fragment_artist.view.*
import kotlinx.coroutines.*
import java.util.*

class ArtistFragment : Fragment() {

    val args: ArtistFragmentArgs by navArgs()

    lateinit var artist: Artist

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val navbar: BottomNavigationView = requireActivity().findViewById(R.id.home_bottom_nav)
        navbar.visibility = View.GONE
        return inflater.inflate(R.layout.fragment_artist, container, false)
    }

    @DelicateCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.backButton.setOnClickListener {
            it.findNavController().popBackStack()
        }

        artist = args.artist
        fetchArtistByArtist(view, artist)

        view.add_to_fav.setOnClickListener{
            val db = Room.databaseBuilder(
                requireContext(),
                FavorisRoomDb::class.java, "favoris"
            ).allowMainThreadQueries().build()
            val favsDao = db.favDao()
            favsDao.insertArtist(
                Artist(null,artist.idArtist,
                    artist.strArtist,
                    artist.strArtistAlternate,
                    artist.strGenre,
                    artist.strBiographyEN,
                    artist.strBiographyFR,
                    artist.strCountry,
                    artist.strArtistFanart2,
                    artist.strArtistThumb,
                    artist.strArtistWideThumb)
            )
            view.favicon.visibility = View.VISIBLE
            view.add_to_fav.visibility = View.INVISIBLE
        }

        view.favicon.setOnClickListener{
            val db = Room.databaseBuilder(
                requireContext(),
                FavorisRoomDb::class.java, "favoris"
            ).allowMainThreadQueries().build()
            val favsDao = db.favDao()
            favsDao.deleteArtistFav(
                artist.idArtist!!
            )
            view.favicon.visibility = View.INVISIBLE
            view.add_to_fav.visibility = View.VISIBLE

        }

        //hide bottom bar
    }


    @DelicateCoroutinesApi
    private fun fetchArtistByArtist(view: View, artist: Artist){
        GlobalScope.launch {
            val responseAlbum = ApiClient.apiService.getAllalbumByArtist(artist.strArtist)
            val responseTracks = ApiClient.apiService.getTopTracks(artist.strArtist)
            withContext(Dispatchers.Main) {

                if (!artist.strArtistFanart2.isNullOrEmpty()){
                    Picasso.get().load(artist.strArtistFanart2).into(view.background_img)
                }else{
                    Picasso.get().load(artist.strArtistWideThumb).into(view.background_img)
                }

                view.artistName.text = artist.strArtist
                view.subTextArtist.text = String.format("%s %s", artist.strCountry, artist.strGenre)

                val db = Room.databaseBuilder(
                    requireContext(),
                    FavorisRoomDb::class.java, "favoris"
                ).allowMainThreadQueries().build()
                val favsDao = db.favDao()

                val favList: List<Artist> = favsDao.getArtistsFav()
                for (i in favList.indices){
                    if (artist.idArtist!!.equals(favList.get(i).idArtist)){
                        view.add_to_fav.visibility = View.INVISIBLE
                        view.favicon.visibility = View.VISIBLE
                    }
                }

                view.description_artist.text =
                    if (artist.strBiographyFR != null && Locale.getDefault().displayLanguage == "français") artist.strBiographyFR else artist.strBiographyEN
                view.description_artist.movementMethod = ScrollingMovementMethod()
                if (responseAlbum.body() != null){
                    val albumBody  = responseAlbum.body()!!
                    if (albumBody.album != null){
                        val tabAlbum : List<Album> = albumBody.album
                        view.recordTextSearch.text = String.format("%s (%s)", getString(R.string.album), tabAlbum.count())
                        view.recyclerRecordDetails.layoutManager = LinearLayoutManager(requireContext())
                        view.recyclerRecordDetails.adapter = ItemListAdapter<Album>(albumBody.album, requireContext(), isArtistView = true) {
                            val action = ArtistFragmentDirections.actionArtistFragmentToAlbumFragment(it)
                            findNavController().navigate(action)
                        }
                    }
                }

                if (responseTracks.body() != null){
                    val tracksBody = responseTracks.body()!!
                    if (tracksBody.track != null){
                        view.recyclerTracksDetails.layoutManager = LinearLayoutManager(requireContext())
                        view.recyclerTracksDetails.adapter = ItemListAdapter<Track>(tracksBody.track, requireContext()){
                            //val action = ArtistFragmentDirections.actionArtistFragmentToSongFragment(it)
                            //findNavController().navigate(action)
                        }
                    }
                }
            }
        }
    }



}