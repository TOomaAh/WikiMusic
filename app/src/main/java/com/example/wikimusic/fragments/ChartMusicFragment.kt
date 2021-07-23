package com.example.wikimusic.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wikimusic.R
import com.example.wikimusic.adapters.ItemListAdapter
import com.example.wikimusic.models.Artist
import com.example.wikimusic.models.Track
import com.example.wikimusic.models.Trending
import com.example.wikimusic.services.ApiClient
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_chart_music.view.*
import kotlinx.coroutines.*

class ChartMusicFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val navbar: BottomNavigationView = requireActivity().findViewById(R.id.home_bottom_nav)
        navbar.visibility = View.VISIBLE
        return inflater.inflate(R.layout.fragment_chart_music, container, false)
    }

    @DelicateCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.recycler_chart_music_track.layoutManager = LinearLayoutManager(requireContext())

        GlobalScope.launch {
            val tracks = ApiClient.apiService.rating("singles")
            val body = tracks.body()
            if (body != null){
                if (body.trending != null){
                    var tracks: List<Trending> = body.trending
                    tracks = tracks.reversed()
                    withContext(Dispatchers.Main){
                        view.recycler_chart_music_track.adapter = ItemListAdapter<Trending>(tracks, requireContext()){
                            GlobalScope.launch {
                                val artistId = it.idArtist
                                val response = ApiClient.apiService.getArtistInfo(artistId!!)
                                if (response.body() != null){
                                    withContext(Dispatchers.Main){
                                        val responseArtist = response.body()!!.artists
                                        val action = ChartsFragmentDirections.actionChartsFragmentToArtistFragment(responseArtist[0])
                                        findNavController().navigate(action)
                                    }

                                }
                            }

                        }
                    }
                }
            }
        }
//        GlobalScope.launch {
//            val topTracks = ApiClient.apiService.rating("singles")
//            val bodyTracks = topTracks.body()
//            if (bodyTracks != null){
//                withContext(Dispatchers.Main){
//                    if (bodyTracks.trending != null){
//                        var tracks : List<Trending> = topTracks.body()!!.trending
//                        tracks = tracks.asReversed()
//                        view.recycler_chart_music_track.adapter = ItemListAdapter<Trending>(tracks, requireContext()){
//                            GlobalScope.launch {
//                                val result = ApiClient.apiService.getArtistInfo(it.idArtist)
//                                val body = result.body()
//                                if (body != null){
//                                    val artists = body.artists
//                                    withContext(Dispatchers.Main) {
//                                        if (!artists.isNullOrEmpty()){
//                                            val artist = artists[0]
//                                            val action = ChartsFragmentDirections.actionChartsFragmentToArtistFragment(artist)
//
//                                            view.findNavController().navigate(action)
//                                        }
//
//                                    }
//
//                                }
//
//                            }
//
//                        }
//                    }
//                }
//            }
//        }
    }
}