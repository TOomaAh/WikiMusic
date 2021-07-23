package com.example.wikimusic.fragments

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wikimusic.R
import com.example.wikimusic.adapters.ItemListAdapter
import com.example.wikimusic.models.Album
import com.example.wikimusic.models.Artist
import com.example.wikimusic.services.ApiClient
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Flow

class SearchFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val navbar: BottomNavigationView = requireActivity().findViewById(R.id.home_bottom_nav)
        navbar.visibility = View.VISIBLE
        return inflater.inflate(R.layout.fragment_search, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.cancel_search.setOnClickListener {
            view.searchEdit.setText("")
            view.recyclerArtistSearch.layoutManager = LinearLayoutManager(requireContext())
            view.recyclerArtistSearch.adapter = ItemListAdapter<Artist>(emptyList(), requireContext()) {}
            view.recyclerRecordSearch.layoutManager = LinearLayoutManager(requireContext())
            view.recyclerRecordSearch.adapter = ItemListAdapter<Album>(emptyList(), requireContext()){}
        }

        searchEdit.setOnEditorActionListener(object: TextView.OnEditorActionListener {
            override fun onEditorAction(
                v: TextView?,
                actionId: Int,
                event: KeyEvent?
            ): Boolean {
                view.recyclerArtistSearch.layoutManager = LinearLayoutManager(requireContext())
                view.recyclerArtistSearch.adapter = ItemListAdapter<Artist>(emptyList(), requireContext()) {}

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (v != null){
                        GlobalScope.launch {
                            val responseArtist = ApiClient.apiService.searchArtist(v.text.toString())
                            val responseAlbum = ApiClient.apiService.getAllalbumByArtist(v.text.toString())
                            val bodyArtist = responseArtist.body()
                            val bodyAlbum = responseAlbum.body()
                            if (bodyArtist != null) {
                                withContext(Dispatchers.Main){
                                    view.recyclerArtistSearch.layoutManager = LinearLayoutManager(requireContext())
                                    if (bodyArtist.artists != null){
                                        val artists: List<Artist> = responseArtist.body()!!.artists
                                        view.recyclerArtistSearch.adapter = ItemListAdapter<Artist>(artists, requireContext()) {
                                            val action = SearchFragmentDirections.actionSearchFragmentToArtistFragment2(it)
                                            findNavController().navigate(action)
                                        }
                                    }else{
                                        view.recyclerArtistSearch.adapter = ItemListAdapter<Artist>(
                                            emptyList(), requireContext()) {

                                        }
                                    }
                                }
                            }

                            if (bodyAlbum != null) {
                                withContext(Dispatchers.Main){
                                    view.recyclerRecordSearch.layoutManager = LinearLayoutManager(requireContext())
                                    if (bodyAlbum.album != null){
                                        val album: List<Album> = responseAlbum.body()!!.album
                                        view.recyclerRecordSearch.adapter = ItemListAdapter<Album>(album, requireContext()) {
                                            val action = SearchFragmentDirections.actionSearchFragmentToAlbumFragment(it)
                                            findNavController().navigate(action)
                                        }
                                        }else{
                                        view.recyclerRecordSearch.adapter = ItemListAdapter<Album>(
                                            emptyList(), requireContext()) {

                                        }
                                    }
                                }
                            }

                        }
                    }
                    return true
                }
                return false
            }
        })
    }
}