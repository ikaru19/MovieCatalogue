package com.syafrizal.submission2.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.syafrizal.submission2.Models.Movie;
import com.syafrizal.submission2.R;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private ArrayList<Movie> movies;
    private Context context;
    public OnAdapterClickListener listener;
    private String type;

    public interface OnAdapterClickListener {
        void DetailonClick(Movie movie);
    }

    public MovieAdapter(Context context, String type) {
        this.context = context;
        this.type = type;
    }

    public void setListener(OnAdapterClickListener listener) {
        this.listener = listener;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_movies, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder viewHolder, int i) {
        runEnterAnimation(viewHolder.itemView);
        Movie movie = movies.get(i);
        String image_url = movie.getImagePoster();
        if (this.type == "movie") {
            viewHolder.titleText.setText(movie.getTitle());
        } else {
            viewHolder.titleText.setText(movie.getName());
        }

        viewHolder.descText.setText(movie.getOverview());
        Picasso.get()
                .load(image_url)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(viewHolder.posterImage);
    }

    private void runEnterAnimation(View view) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        view.setTranslationY(display.getHeight());
        view.animate()
                .translationY(0)
                .setInterpolator(new DecelerateInterpolator(3.f))
                .setDuration(700)
                .start();
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
