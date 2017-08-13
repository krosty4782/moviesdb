package com.testapplication.moviedetail.view;

import android.view.View;
import android.widget.TextView;

import com.testapplication.moviedetail.presenter.MovieDetailPresenter;
import com.testapplication.moviesapi.R;
import com.testapplication.moviesplaying.model.Movie;
import com.testapplication.moviesplaying.view.BaseViewHolder;

public class CollectionsViewHolder extends BaseViewHolder implements View.OnClickListener {

    private final MovieDetailPresenter movieDetailPresenter;
    private Movie movie;
    private TextView titleView;

    public CollectionsViewHolder(View itemView, MovieDetailPresenter movieDetailPresenter) {
        super(itemView);
        this.movieDetailPresenter = movieDetailPresenter;
        itemView.setOnClickListener(this);
        titleView = (TextView) itemView.findViewById(R.id.item_title);
    }

    @Override
    public void onClick(View view) {
        movieDetailPresenter.onMovieSelected(movie);
    }

    @Override
    public void onBind(Movie movie) {
        this.movie = movie;
        titleView.setText(movie.getTitle());
    }
}
