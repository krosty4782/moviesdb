package com.testapplication.moviesplaying.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.testapplication.moviesapi.R;
import com.testapplication.moviesplaying.model.Movie;
import com.testapplication.moviesplaying.presenter.MovieListPresenter;

import java.util.ArrayList;
import java.util.List;


public class MoviesAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Movie> movies = new ArrayList<>();
    private MovieListPresenter movieListPresenter;

    public void setData(List<Movie> data) {
        movies = data;
        notifyDataSetChanged();
    }

    MoviesAdapter(@NonNull MovieListPresenter movieListPresenter) {
        this.movieListPresenter = movieListPresenter;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_movie, parent, false);
        return new MoviesViewHolder(view, movieListPresenter);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
