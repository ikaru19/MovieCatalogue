package com.syafrizal.submission2.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.syafrizal.submission2.Models.Movie;
import com.syafrizal.submission2.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private List<Movie> movies;
    private Context context;
    public OnAdapterClickListener listener;

    public interface OnAdapterClickListener{
        void DetailonClick(Movie movie);
    }

    public MovieAdapter(Context context) {
        this.context = context;
    }

    public void setListener(OnAdapterClickListener listener) {
        this.listener = listener;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_movies,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder viewHolder, int i) {
        Movie movie = movies.get(i);
        viewHolder.titleText.setText(movie.getTitle());
        viewHolder.descText.setText(movie.getDesc());
        viewHolder.posterImage.setImageResource(movie.getPoster());
    }

    @Override
    public int getItemCount() {
        return (movies != null) ? movies.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleText;
        TextView descText;
        ImageView posterImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.text_title);
            descText = itemView.findViewById(R.id.text_desc);
            posterImage = itemView.findViewById(R.id.image_poster);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.DetailonClick(movies.get(getAdapterPosition()));
                }
            });

        }
    }
}
