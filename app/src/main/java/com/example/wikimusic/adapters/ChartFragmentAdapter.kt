package com.example.wikimusic.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.wikimusic.fragments.ChartAlbumFragment
import com.example.wikimusic.fragments.ChartMusicFragment

class ChartFragmentAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    var context: Context?,
    var totalTabs: Int
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return totalTabs
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                ChartMusicFragment()
            }
            1 -> {
                ChartAlbumFragment()
            }
            else -> ChartMusicFragment()
        }
    }

}