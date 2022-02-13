package edu.uci.swe264p.retrofit;

import com.google.gson.annotations.SerializedName;

public class TopRatedResponse {
    @SerializedName("vote_average")
    private Float voteAverage;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("title")
    private String title;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("overview")
    private String overview;

    public TopRatedResponse(Float voteAverage, String posterPath, String title, String releaseDate, String overview) {
        this.voteAverage = voteAverage;
        this.posterPath = posterPath;
        this.title = title;
        this.releaseDate = releaseDate;
        this.overview = overview;
    }
    // ... Getters for the above fields

    public Float getVoteAverage() {
        return this.voteAverage;
    }

    public String getPosterPath() {
        return this.posterPath;
    }

    public String getTitle() {
        return this.title;
    }

    public String getReleaseDate() {
        return this.releaseDate;
    }

    public String getOverview() {
        return this.overview;
    }

}
