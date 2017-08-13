package com.testapplication.moviedetail.dagger;

import com.testapplication.moviedetail.presenter.MovieDetailPresenter;
import com.testapplication.moviedetail.presenter.MovieDetailPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieDetailModule {

    @Provides
    @MovieDetailScope
    public MovieDetailPresenter provideMovieDetailPresenter() {
        return new MovieDetailPresenterImpl();
    }
}
