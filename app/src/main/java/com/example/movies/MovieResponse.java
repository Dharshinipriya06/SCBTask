package com.example.movies;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieResponse {
    @SerializedName("Search")
    public ArrayList<Search> search;
    @SerializedName("totalResults")
    public String totalResults;
    @SerializedName("Response")
    public String response;
    @SerializedName("Error")
    public String error;

    public ArrayList<Search> getSearch() {
        return search;
    }

    public void setSearch(ArrayList<Search> search) {
        this.search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}




