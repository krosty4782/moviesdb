package com.testapplication;

import android.app.Application;

import com.testapplication.moviesapi.base.dagger.ApplicationComponent;
import com.testapplication.moviesapi.base.dagger.ApplicationModule;
import com.testapplication.moviesapi.base.dagger.DaggerApplicationComponent;


public class MoviesApplication extends Application {

    private final ApplicationComponent applicationComponent = createComponent();

    @Override
    public void onCreate() {
        super.onCreate();
    }


    protected ApplicationComponent createComponent() {
        return DaggerApplicationComponent.builder().applicationModule(new ApplicationModule())
                .build();
    }


    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
