package com.testapplication.base.dagger;

import com.testapplication.base.services.MoviesService;

import dagger.Component;

@ApplicationScope
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    MoviesService moviesService();
}