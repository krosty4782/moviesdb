package com.testapplication.moviedetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.testapplication.MoviesApplication;
import com.testapplication.moviedetail.dagger.DaggerMovieDetailComponent;
import com.testapplication.moviedetail.dagger.MovieDetailModule;
import com.testapplication.moviedetail.presenter.MovieDetailPresenter;
import com.testapplication.moviesapi.R;
import com.testapplication.moviesplaying.model.Movie;

import javax.inject.Inject;


public class MovieDetailActivity extends AppCompatActivity implements MovieDetailPresenter.View {

    private static final String EXTRA_MOVIE = "extraMovie";

    @Inject
    MovieDetailPresenter presenter;

    public static Intent createIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        DaggerMovieDetailComponent.builder()
                .applicationComponent(((MoviesApplication) getApplication()).getApplicationComponent())
                .movieDetailModule(new MovieDetailModule())
                .build()
                .inject(this);
        presenter.onInitialise((Movie) getIntent().getExtras().getSerializable(EXTRA_MOVIE));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    public void showMovieDetails(Movie movie) {
        ((TextView) findViewById(R.id.detail_title)).setText(movie.getTitle());
        ((TextView) findViewById(R.id.detail_overview)).setText(movie.getOverview());
    }
}
