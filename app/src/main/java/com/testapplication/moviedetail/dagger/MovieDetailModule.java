package com.testapplication.moviedetail.dagger;

import android.support.annotation.NonNull;

import com.testapplication.moviedetail.presenter.MovieDetailPresenter;
import com.testapplication.moviedetail.presenter.MovieDetailPresenterImpl;
import com.testapplication.base.services.MoviesService;

import dagger.Module;
import dagger.Provides;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module
public class MovieDetailModule {

    @Provides
    @MovieDetailScope
    public MovieDetailPresenter provideMovieDetailPresenter(@NonNull MoviesService moviesService) {
        return new MovieDetailPresenterImpl(moviesService, AndroidSchedulers.mainThread(), Schedulers.io());
    }
}
