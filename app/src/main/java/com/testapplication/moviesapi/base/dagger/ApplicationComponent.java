package com.testapplication.moviesapi.base.dagger;

import com.testapplication.moviesapi.base.services.MoviesService;

import dagger.Component;

@ApplicationScope
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    MoviesService moviesService();
}