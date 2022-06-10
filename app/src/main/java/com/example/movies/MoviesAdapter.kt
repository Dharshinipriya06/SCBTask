package com.example.movies

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.MoviesAdapter.MovieViewHolder
import android.widget.TextView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso
import java.util.ArrayList

class MoviesAdapter(
    private val movies: ArrayList<Search>,
    private val rowLayout: Int,
    private val context: Context
) : RecyclerView.Adapter<MovieViewHolder>() {
    class MovieViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var movieTitle: TextView
        var poster: ImageView

        init {
            movieTitle = v.findViewById<View>(R.id.movieTitle) as TextView
            poster = v.findViewById<View>(R.id.movieImg) as ImageView
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(rowLayout, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.movieTitle.text = movies[position].title
        Picasso.get().load(movies[position].poster)
            .placeholder(R.drawable.loader)
            .into(holder.poster)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}