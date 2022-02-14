package edu.uci.swe264p.retrofit;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textservice.TextInfo;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder>{
    private List<Movie> mData;

    MovieListAdapter(List<Movie> data) {
        this.mData = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        TextView releaseDate;
        TextView tvVote;
        TextView tvOverView;
        ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tvTitle);
            releaseDate = itemView.findViewById((R.id.tvReleaseDate));
            tvVote = itemView.findViewById(R.id.tvVote);
            tvOverView = itemView.findViewById(R.id.tvOverview);



        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie program = mData.get(position);
        holder.tv.setText(program.getTitle());
        holder.releaseDate.setText(program.getReleaseDate());
        holder.tvVote.setText(program.getVoteAverage().toString());
        holder.tvOverView.setText(program.getOverview());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }



}
