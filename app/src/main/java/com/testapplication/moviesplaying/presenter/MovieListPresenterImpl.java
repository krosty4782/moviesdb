package com.testapplication.moviesplaying.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.testapplication.moviesapi.base.services.MoviesService;
import com.testapplication.moviesplaying.model.Movie;

import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

public class MovieListPresenterImpl implements MovieListPresenter {

    private CompositeSubscription subscriptions = new CompositeSubscription();
    private MoviesService moviesService;
    @NonNull
    private final Scheduler notifications;
    @NonNull
    private final Scheduler worker;
    private View view;

    public MovieListPresenterImpl(@NonNull MoviesService moviesService,
                                  @NonNull Scheduler notifications,
                                  @NonNull Scheduler worker) {

        this.moviesService = moviesService;
        this.notifications = notifications;
        this.worker = worker;
    }

    @Override
    public void attachView(View view) {
        this.view = view;
        subscriptions.add(moviesService.nowPlaying()
                .observeOn(notifications)
                .subscribeOn(worker)
                .subscribe(view::showMovieList,
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
    public void onMovieSelected(Movie movie) {
        view.showMovieDetail(movie);
    }
}
