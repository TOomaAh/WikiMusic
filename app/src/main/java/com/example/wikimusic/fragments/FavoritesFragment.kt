package com.example.wikimusic.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.wikimusic.R
import com.example.wikimusic.adapters.ItemListAdapter
import com.example.wikimusic.entity.FavorisRoomDb
import com.example.wikimusic.models.Album
import com.example.wikimusic.models.Artist
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_favorites.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val navbar: BottomNavigationView = requireActivity().findViewById(R.id.home_bottom_nav)
        navbar.visibility = View.VISIBLE
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GlobalScope.launch {

            val db = Room.databaseBuilder(
                requireContext(),
                FavorisRoomDb::class.java, "favoris"
            ).allowMainThreadQueries().build()
            val favsDao = db.favDao()

            val favListArtist: List<Artist> = favsDao.getArtistsFav()
            val favListAlbum: List<Album> = favsDao.getAlbum()



            if (favListArtist != null) {
                withContext(Dispatchers.Main){
                    view.recyclerArtistFav.layoutManager = LinearLayoutManager(requireContext())
                    if (favListArtist != null){
                        val artists: List<Artist> = favListArtist
                        view.recyclerArtistFav.adapter = ItemListAdapter<Artist>(artists, requireContext()) {
                            val action = FavoritesFragmentDirections.actionFavoritesFragmentToArtistFragment(it)
                            findNavController().navigate(action)
                        }
                    }else{
                        view.recyclerArtistFav.adapter = ItemListAdapter<Artist>(
                            emptyList(), requireContext()) {

                        }
                    }
                }
            }

            if (favListAlbum != null) {
                withContext(Dispatchers.Main){
                    view.recyclerRecordFav.layoutManager = LinearLayoutManager(requireContext())
                    if (favListAlbum != null){
                        val album: List<Album> = favListAlbum
                        view.recyclerRecordFav.adapter = ItemListAdapter<Album>(album, requireContext()) {
                            val action = FavoritesFragmentDirections.actionFavoritesFragmentToAlbumFragment(it)
                            findNavController().navigate(action)
                        }
                    }else{
                        view.recyclerRecordFav.adapter = ItemListAdapter<Album>(
                            emptyList(), requireContext()) {

                        }
                    }
                }
            }

        }

    }
}