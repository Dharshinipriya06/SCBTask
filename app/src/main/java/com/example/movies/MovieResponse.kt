package com.example.movies

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class MovieResponse {
    @SerializedName("Search")
    var search: ArrayList<Search>? = null

    @SerializedName("totalResults")
    var totalResults: String? = null

    @SerializedName("Response")
    var response: String? = null

    @SerializedName("Error")
    var error: String? = null
}