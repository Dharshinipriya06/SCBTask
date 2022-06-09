package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private final static String API_KEY = BuildConfig.API_KEY;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SearchView simpleSearchView = (SearchView) findViewById(R.id.searchList); // inititate a search view

        simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getSearchMovies(query, "movie");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getSearchMovies(newText, "movie");
                return false;
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

    }

    private void getSearchMovies(String newText, String movie) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MovieResponse> call = apiService.getTopRatedMovies("", API_KEY, newText, movie);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                String resListmovies = response.body().getResponse();
                // Log.e(TAG, resListmovies);
                if (resListmovies.equals("True")) {
                    ArrayList<Search> movies = response.body().getSearch();
                    recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_layout, getApplicationContext()));
                    Log.d(TAG, "Number of movies received: " + movies.size());
                } else {
                    // Toast.makeText(getApplicationContext(),response.body().getError(),Toast.LENGTH_LONG).show();
                    Log.e(TAG, resListmovies);
                }


            }


            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

}