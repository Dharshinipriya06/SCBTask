package com.example.movies

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val simpleSearchView =
            findViewById<View>(R.id.searchList) as SearchView
        simpleSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                getSearchMovies(query, "movie")
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                getSearchMovies(newText, "movie")
                return false
            }
        })
        recyclerView = findViewById<View>(R.id.recyclerList) as RecyclerView
        recyclerView!!.layoutManager = GridLayoutManager(this, 2)
    }

    private fun getSearchMovies(newText: String, movie: String) {
        val apiService = ApiClient.client?.create(ApiInterface::class.java)
        val call = apiService?.getTopRatedMovies("", API_KEY, newText, movie)
        call?.enqueue(object : Callback<MovieResponse?> {
            override fun onResponse(
                call: Call<MovieResponse?>,
                response: Response<MovieResponse?>
            ) {
                val resListmovies = response.body()!!.response
                // Log.e(TAG, resListmovies);
                if (resListmovies == "True") {
                    val movies = response.body()!!.search
                    recyclerView!!.adapter =
                        movies?.let { MoviesAdapter(it, R.layout.list_layout, applicationContext) }
                } else {

                    if (resListmovies != null) {
                        Log.e(TAG, resListmovies)
                    }
                }

            }

            override fun onFailure(call: Call<MovieResponse?>, t: Throwable) {

                Log.e(TAG, t.toString())
            }
        })
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
        private const val API_KEY = BuildConfig.API_KEY
    }
}