package com.example.wikimusic.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
@Entity(tableName = "artist")
@Parcelize
class Artist(@PrimaryKey(autoGenerate = true) val id: Int?,
             @ColumnInfo(name = "idArtist") val idArtist: String?,
             @ColumnInfo(name = "strArtist") val strArtist: String,
             @ColumnInfo(name = "strArtistAlternate") val strArtistAlternate: String?,
             @ColumnInfo(name = "strGenre") val strGenre: String?,
             @ColumnInfo(name = "strBiographyEN") val strBiographyEN: String?,
             @ColumnInfo(name = "strBiographyFR") val strBiographyFR: String?,
             @ColumnInfo(name = "strCountry") val strCountry: String?,
             @ColumnInfo(name = "strArtistThumb") val strArtistThumb: String?,
             @ColumnInfo(name = "strArtistWideThumb") val strArtistWideThumb: String?,
) : Parcelable