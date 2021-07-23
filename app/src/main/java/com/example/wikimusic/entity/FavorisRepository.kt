package com.example.wikimusic.entity

import android.support.annotation.WorkerThread
import com.example.wikimusic.models.Artist

class FavorisRepository(private val favDao: FavorisDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allWords: List<Artist> = favDao.getArtistsFav()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertArtist(fav: Artist) {
        favDao.insertArtist(fav)
    }
}