package com.testapplication.moviedetail.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.testapplication.moviedetail.presenter.MovieDetailPresenter;
import com.testapplication.moviesapi.R;
import com.testapplication.moviesplaying.model.Movie;
import com.testapplication.moviesplaying.view.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;


public class CollectionsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Movie> movies = new ArrayList<>();
    private MovieDetailPresenter movieDetailsPresenter;

    public void setData(List<Movie> data) {
        movies = data;
        notifyDataSetChanged();
    }

    CollectionsAdapter(@NonNull MovieDetailPresenter movieDetailsPresenter) {
        this.movieDetailsPresenter = movieDetailsPresenter;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_movie_collections, parent, false);
        return new CollectionsViewHolder(view, movieDetailsPresenter);
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
