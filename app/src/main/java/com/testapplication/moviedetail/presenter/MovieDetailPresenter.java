package com.testapplication.moviedetail.presenter;


import com.testapplication.moviesapi.base.presenter.Presenter;
import com.testapplication.moviesapi.base.presenter.PresenterView;
import com.testapplication.moviesplaying.model.Movie;

public interface MovieDetailPresenter extends Presenter<MovieDetailPresenter.View> {

    void onInitialise(Movie movie);

    interface View extends PresenterView {
        void showMovieDetails(Movie movie);
    }
}
