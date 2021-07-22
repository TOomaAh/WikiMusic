package com.example.wikimusic.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "favoris")
data class Favoris(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "artist_id") val artist_id: String)

/* val db = Room.databaseBuilder(
            requireContext(),
            FavorisRoomDb::class.java, "favoris"
        ).allowMainThreadQueries().build()
        val favsDao = db.favDao()
       // favsDao.insert(Favoris(null,"1233"))
       // favsDao.delete()
        val favList: List<Favoris> = favsDao.getArtistsFav()
        System.out.println(favList) */
