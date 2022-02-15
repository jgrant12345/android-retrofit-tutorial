package edu.uci.swe264p.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MovieListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    static Retrofit retrofit = null;

    static final String BASE_URL = "https://api.themoviedb.org/3/";
    final static String API_KEY = "856bdec268693b5f42fa58d720946353";

    public static ArrayList<Movie> moviesList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);



        connect();

    }

    private void connect() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        MovieApiService movieApiService = retrofit.create(MovieApiService.class);
        Call<TopRatedResponse> call = movieApiService.getTopMovies(API_KEY);
        call.enqueue(new Callback<TopRatedResponse>() {
            @Override
            public void onResponse(Call<TopRatedResponse> call, Response<TopRatedResponse> response) {
                int[] ids = {R.id.txtTitle, R.id.txtReleaseDate, R.id.txtPoster,
                        R.id.txtVote, R.id.txtOverview};

                int movieListSize = response.body().getMovieList().size();
//                get top 20 movies
                for(int i =0; i < 20; i++){
                    if(i >= movieListSize){
                        break;
                    }
                    moviesList.add(response.body().getMovieList().get(i));
                }

                recyclerView = findViewById(R.id.rvMovieList);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(MovieListActivity.this));
                recyclerView.setAdapter(new MovieListAdapter(moviesList));

            }
            @Override
            public void onFailure(Call<TopRatedResponse> call, Throwable throwable) {
                Log.e("failed", throwable.toString());
            }
        });
    }


}
