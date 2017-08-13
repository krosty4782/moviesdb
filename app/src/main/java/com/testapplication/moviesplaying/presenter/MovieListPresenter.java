package com.testapplication.moviesplaying.presenter;


import com.testapplication.moviesapi.base.presenter.Presenter;
import com.testapplication.moviesapi.base.presenter.PresenterView;
import com.testapplication.moviesplaying.model.Movie;

import java.util.List;


public interface MovieListPresenter extends Presenter<MovieListPresenter.View> {

    void onMovieSelected(Movie movie);

    interface View extends PresenterView {
        void showMovieList(List<Movie> movieList);

        void showMovieDetail(Movie movie);
    }
}
