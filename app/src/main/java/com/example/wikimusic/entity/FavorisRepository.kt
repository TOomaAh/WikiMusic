package com.example.wikimusic.entity

import android.support.annotation.WorkerThread
import com.example.wikimusic.models.Artist
import org.intellij.lang.annotations.Flow

class FavorisRepository(private val favDao: FavorisDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allWords: List<Favoris> = favDao.getArtistsFav()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(fav: Favoris) {
        favDao.insert(fav)
    }
}