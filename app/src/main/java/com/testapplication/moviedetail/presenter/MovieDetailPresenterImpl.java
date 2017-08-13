package com.testapplication.moviedetail.presenter;

import com.testapplication.moviesplaying.model.Movie;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by mfolcini on 17/07/2017.
 */

public class MovieDetailPresenterImpl implements MovieDetailPresenter {

    private CompositeSubscription subscriptions = new CompositeSubscription();
    private View view;
    private Movie movie;

    public MovieDetailPresenterImpl() {

    }

    @Override
    public void attachView(View view) {
        this.view = view;
        view.showMovieDetails(movie);
    }

    @Override
    public void detachView() {
    }

    @Override
    public void destroy() {
        subscriptions.clear();
    }

    @Override
    public void onInitialise(Movie movie) {

        this.movie = movie;
    }
}
