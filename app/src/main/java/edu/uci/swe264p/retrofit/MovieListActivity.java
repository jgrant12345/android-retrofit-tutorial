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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        List<String> programs = new ArrayList<String>(
                Arrays.asList(
                        "Computer Science — Ph.D.",
                        "Computer Science — M.S.",
                        "Informatics — Ph.D.",
                        "Informatics — M.S.",
                        "Networked Systems — Ph.D.",
                        "Networked Systems — Ph.D.",
                        "Software Engineering — Ph.D.",
                        "Software Engineering — M.S.",
                        "Statistics — Ph.D.",
                        "Statistics — M.S.",
                        "Master of Computer Science (MCS)",
                        "Master of Software Engineering (MSWE)",
                        "Master of Human-Computer Interaction and Design (MHCID)",
                        "Master of Embedded & Cyber-physical Systems (MECPS)"
                )
        );


        connect();
        recyclerView = findViewById(R.id.rvMovieList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ProgramListAdapter(programs));
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

                String[] values = {


//                        response.body().getTitle(),
//                        response.body().getReleaseDate(),
//                        response.body().getPosterPath(),
//                        response.body().getVoteAverage().toString(),
//                        response.body().getOverview()
                };
                System.out.println(response.body().getMovieList().get(0).getTitle());

            }
            @Override
            public void onFailure(Call<TopRatedResponse> call, Throwable throwable) {
                Log.e("failed", throwable.toString());
            }
        });
    }


}
