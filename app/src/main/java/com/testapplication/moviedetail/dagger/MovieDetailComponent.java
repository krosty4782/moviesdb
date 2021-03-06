package com.testapplication.moviedetail.dagger;


import com.testapplication.moviedetail.view.MovieDetailActivity;
import com.testapplication.base.dagger.ApplicationComponent;

import dagger.Component;


@MovieDetailScope
@Component(dependencies = ApplicationComponent.class, modules = MovieDetailModule.class)
public interface MovieDetailComponent {

    void inject(MovieDetailActivity activity);
}
