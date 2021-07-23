package com.example.wikimusic.entity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wikimusic.models.Album
import com.example.wikimusic.models.Artist

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(Artist::class, Album::class), version = 4, exportSchema = false)
public abstract class FavorisRoomDb : RoomDatabase() {

    abstract fun favDao(): FavorisDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: FavorisRoomDb? = null

        fun getDatabase(context: Context): FavorisRoomDb {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavorisRoomDb::class.java,
                    "favoris"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}