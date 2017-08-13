package com.testapplication.moviesplaying.dagger;

import com.testapplication.base.dagger.ApplicationComponent;
import com.testapplication.moviesplaying.view.MovieListActivity;

import dagger.Component;


@MovieListScope
@Component(dependencies = ApplicationComponent.class, modules = MovieListModule.class)
public interface MovieListComponent {

    void inject(MovieListActivity activity);
}
