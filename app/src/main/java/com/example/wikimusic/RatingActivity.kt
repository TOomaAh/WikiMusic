package com.example.wikimusic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.wikimusic.models.Album
import com.example.wikimusic.models.ResultAlbum
import com.example.wikimusic.models.ResultArtist
import com.example.wikimusic.models.ResultTrending
import com.example.wikimusic.services.ApiClient
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RatingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)
    }

    //FORMAT AUTORISé: singles & albums
    fun getRating(format: String) {
        runBlocking {
            launch {
                try {
                    val response = ApiClient.apiService.rating(format)
                    if (response.body() != null && response.isSuccessful) {
                         //TODO PEUPLER LES INFO VOIR MODELE TRENDING : List<Trending>
                    } else {
                        //TODO AFFICHER LE RELOAD SI ERROR
                        Toast.makeText(
                            this@RatingActivity,
                            "Error Occurred: ${response.message()}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }catch (e: Exception) {
                    //TODO AFFICHER LE RELOAD SI ERROR
                    Toast.makeText(
                        this@RatingActivity,
                        "Error Occurred: ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }


            }
        }
    }
}