package com.testapplication.moviesplaying.dagger;

import android.support.annotation.NonNull;

import com.testapplication.base.services.MoviesService;
import com.testapplication.moviesplaying.presenter.MovieListPresenter;
import com.testapplication.moviesplaying.presenter.MovieListPresenterImpl;

import dagger.Module;
import dagger.Provides;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


@Module
public class MovieListModule {

    @Provides
    @MovieListScope
    public MovieListPresenter provideMovieListPresenter(@NonNull MoviesService moviesService) {
        return new MovieListPresenterImpl(moviesService, AndroidSchedulers.mainThread(), Schedulers.io());
    }
}
