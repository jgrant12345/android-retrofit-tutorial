package edu.uci.swe264p.retrofit;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TopRatedResponse {
    @SerializedName("results")
    private ArrayList<Movie> movieList;

    public TopRatedResponse(ArrayList<Movie> movieList) {
        this.movieList = movieList;
    }

    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

}
