package com.example.wikimusic.models

class ResultArtist(private val artist: List<Artist>) {

    override fun toString(): String {
        return artist[0].toString()
    }
}