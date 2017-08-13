package com.testapplication.moviesplaying.view;

import android.view.View;
import android.widget.TextView;

import com.testapplication.moviesapi.R;
import com.testapplication.moviesplaying.model.Movie;
import com.testapplication.moviesplaying.presenter.MovieListPresenter;

public class MoviesViewHolder extends BaseViewHolder implements View.OnClickListener {

    private final MovieListPresenter movieListPresenter;
    private Movie movie;
    private TextView titleView;

    MoviesViewHolder(View itemView, MovieListPresenter movieListPresenter) {
        super(itemView);
        this.movieListPresenter = movieListPresenter;
        itemView.setOnClickListener(this);
        titleView = (TextView) itemView.findViewById(R.id.item_title);
    }

    @Override
    public void onClick(View view) {
        movieListPresenter.onMovieSelected(movie);
    }

    @Override
    public void onBind(Movie movie) {
        this.movie = movie;
        titleView.setText(movie.getTitle());
    }
}
