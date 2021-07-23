package com.example.wikimusic.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
@Entity(tableName = "album")
@Parcelize
class Album(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "idAlbum") val idAlbum: String?,
    @ColumnInfo(name = "strAlbum")val strAlbum: String?,
    @ColumnInfo(name = "strArtist")val strArtist: String?,
    @ColumnInfo(name = "intYearReleased") val intYearReleased: String?,
    @ColumnInfo(name = "strAlbumThumb")val strAlbumThumb: String?,
    @ColumnInfo(name = "strDescriptionEN") val strDescriptionEN: String?,
    @ColumnInfo(name = "strDescriptionFR")val strDescriptionFR: String?,
    @ColumnInfo(name = "intScore")val intScore: String?,
    @ColumnInfo(name = "intScoreVotes")val intScoreVotes: String?,
): Parcelable {
}