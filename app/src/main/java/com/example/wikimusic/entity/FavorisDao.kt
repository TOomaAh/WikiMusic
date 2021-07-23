package com.example.wikimusic.entity

import androidx.room.*
import com.example.wikimusic.models.Album
import com.example.wikimusic.models.Artist

@Dao
interface FavorisDao {

    @Query("SELECT * FROM artist")
    fun getArtistsFav(): List<Artist>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertArtist(fav: Artist): Void

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAlbum(fav: Album): Void

    @Query("SELECT * FROM album")
    fun getAlbum(): List<Album>

    @Query("DELETE FROM artist")
    fun delete()

    @Query("DELETE FROM album WHERE idAlbum = :idAlbum")
    fun deleteAlbumFav(idAlbum: String)

    @Query("DELETE FROM artist WHERE idArtist = :idArtist")
    fun deleteArtistFav(idArtist: String)

}