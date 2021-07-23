package com.example.wikimusic.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wikimusic.R
import com.example.wikimusic.adapters.ItemListAdapter
import com.example.wikimusic.models.Trending
import com.example.wikimusic.services.ApiClient
import kotlinx.android.synthetic.main.fragment_chart_album.view.*
import kotlinx.android.synthetic.main.fragment_chart_music.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChartAlbumFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chart_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalScope.launch {
            val topTracks = ApiClient.apiService.rating("albums")
            val bodyTracks = topTracks.body()
            if (bodyTracks != null){
                withContext(Dispatchers.Main){
                    view.recycler_chart_album.layoutManager = LinearLayoutManager(requireContext())
                    if (bodyTracks.trending != null){
                        var tracks : List<Trending> = topTracks.body()!!.trending
                        tracks = tracks.asReversed()
                        view.recycler_chart_album.adapter = ItemListAdapter<Trending>(tracks, requireContext()){
                            //Get artist by id to send it to action
                            //val action = ChartsFragmentDirections.actionChartsFragmentToArtistFragment()
                        }
                    }
                }
            }
        }
    }
}