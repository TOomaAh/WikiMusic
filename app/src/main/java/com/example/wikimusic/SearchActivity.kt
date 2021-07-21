package com.example.wikimusic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.wikimusic.services.ApiClient
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
    }

    /*fun search(value: String){
        runBlocking {
            launch {
                try {
                    val response = ApiClient.apiService.search(value)
                    if (response.body() != null && response.isSuccessful) {
                        //TODO PEUPLER LES INFO VOIR MODELE TRENDING : List<Trending>
                    } else {
                        //TODO AFFICHER LE RELOAD SI ERROR
                        Toast.makeText(
                            this@SearchActivity,
                            "Error Occurred: ${response.message()}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }catch (e: Exception) {
                    //TODO AFFICHER LE RELOAD SI ERROR
                    Toast.makeText(
                        this@SearchActivity,
                        "Error Occurred: ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }*/
}