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
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*

class SearchFragment : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //Selon le prof
//        view.recyclerArtistSearch.layoutManager = LinearLayoutManager(requireContext())
//        view.recyclerArtistSearch.adapter = ItemListAdapter<Artist>(list_artist);
//
//        view.recyclerRecordSearch.layoutManager = LinearLayoutManager(requireContext())
//        view.recyclerRecordSearch.adapter = ItemListAdapter<Album>(list_album);



        recyclerArtistSearch.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = SearchAdapter()
        }
        recyclerRecordSearch.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = SearchAdapter()
        }
    }
}

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_artist_cell, parent, false)
        return ViewHolder(v);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 5
    }
}