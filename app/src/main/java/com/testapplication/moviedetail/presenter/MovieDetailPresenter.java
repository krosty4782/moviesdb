package com.testapplication.moviedetail.presenter;


import com.testapplication.moviesapi.base.presenter.Presenter;
import com.testapplication.moviesapi.base.presenter.PresenterView;
import com.testapplication.moviesplaying.model.Movie;

import java.util.List;

public interface MovieDetailPresenter extends Presenter<MovieDetailPresenter.View> {

    void onInitialise(Movie movie);

    void onMovieSelected(Movie movie);

    interface View extends PresenterView {
        void showMovieDetails(Movie movie);

        void showCollectionDetails(Movie movie);

        void showMoviesCollection(List<Movie> movie);
    }
}
