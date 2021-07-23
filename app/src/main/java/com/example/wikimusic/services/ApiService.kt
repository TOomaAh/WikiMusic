package com.example.wikimusic.services

import com.example.wikimusic.models.*
import org.intellij.lang.annotations.MagicConstant
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("search.php") suspend fun searchArtist(@Query("s") search: String) : Response<ResultArtist>
    @GET("trending.php?country=us&type=itunes") suspend fun rating(@Query("format") format: String) : Response<ResultTrending>;
    @GET("searchalbum.php") suspend fun searchAlbum(@Query("s") artist: String, @Query("a") album: String) : Response<ResultAlbum>;
    @GET("searchAlbum.php") suspend fun getAllAlbum(@Query("s") artist: String) : Response<ResultAlbum>;
    @GET("searchalbum.php") suspend fun getAllalbumByArtist(@Query("s") artist: String) : Response<ResultAlbum>
    @GET("artist.php") suspend fun getArtistInfo(@Query("i")artistId: String) : Response<ResultArtist>
    @GET("track-top10.php") suspend fun getTopTracks(@Query("s") artistName: String) : Response<ResultTrack>
    @GET("track.php") suspend fun getTracksByAlbum(@Query("m") albumId: String) : Response<ResultTrack>
}