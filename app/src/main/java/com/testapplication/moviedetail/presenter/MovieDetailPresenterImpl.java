package com.testapplication.moviedetail.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.testapplication.moviesapi.base.services.MoviesService;
import com.testapplication.moviesplaying.model.Movie;

import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mfolcini on 17/07/2017.
 */

public class MovieDetailPresenterImpl implements MovieDetailPresenter {

    private CompositeSubscription subscriptions = new CompositeSubscription();
    private View view;
    private Movie movie;
    private final MoviesService moviesService;
    @NonNull
    private final Scheduler notifications;
    @NonNull
    private final Scheduler worker;

    public MovieDetailPresenterImpl(@NonNull MoviesService moviesService,
                                    @NonNull Scheduler notifications,
                                    @NonNull Scheduler worker) {

        this.moviesService = moviesService;
        this.notifications = notifications;
        this.worker = worker;
    }

    @Override
    public void attachView(View view) {
        this.view = view;
        view.showMovieDetails(movie);

        subscriptions.add(
                moviesService.getInfo(movie.getId())
                        .subscribeOn(worker)
                        .filter(movie1 -> movie1.getCollectionId() != null)
                        .flatMap(filtered -> moviesService.getMoviesCollection(filtered.getCollectionId()))
                        .observeOn(notifications)
                        .subscribe((movie1) -> view.showMoviesCollection(movie1),
                                e -> Log.d("ERROR: ", e.getLocalizedMessage())));
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

    @Override
    public void onMovieSelected(Movie movie) {
        view.showCollectionDetails(movie);
    }
}
