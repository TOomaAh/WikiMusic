package com.example.wikimusic.entity

import androidx.room.*

@Dao
interface FavorisDao {

    @Query("SELECT * FROM favoris")
    fun getArtistsFav(): List<Favoris>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(fav: Favoris): Void

    @Query("DELETE FROM favoris")
    fun delete()

}