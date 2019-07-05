package com.syafrizal.submission2.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.syafrizal.submission2.Models.Movie;
import com.syafrizal.submission2.R;

import java.util.ArrayList;

public class RecomendationAdapter extends RecyclerView.Adapter<RecomendationAdapter.ViewHolder> {
    private ArrayList<Movie> movies;
    private Context context;
    public MovieAdapter.OnAdapterClickListener listener;
    private String type;

    public RecomendationAdapter(Context context, String type) {
        this.context = context;
        this.type = type;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public void setListener(MovieAdapter.OnAdapterClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecomendationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_recomendations, viewGroup, false);
        return new RecomendationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecomendationAdapter.ViewHolder viewHolder, int i) {
        Movie movie = movies.get(i);
        String image_url = movie.getImagePoster();
        if (this.type.equalsIgnoreCase("movie")) {
            viewHolder.textTitle.setText(movie.getTitle());
        } else {
            viewHolder.textTitle.setText(movie.getName());
        }
        Picasso.get()
                .load(image_url)
                .placeholder( R.drawable.progress_animation)
                .error(android.R.drawable.sym_def_app_icon)
                .into(viewHolder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return (movies != null) ? movies.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle;
        ImageView imgPoster;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.text_title_rec);
            imgPoster = itemView.findViewById(R.id.image_rec);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.DetailonClick(movies.get(getAdapterPosition()));
                }
            });
        }
    }
}
