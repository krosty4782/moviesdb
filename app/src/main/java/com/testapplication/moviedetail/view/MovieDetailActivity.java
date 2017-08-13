package com.testapplication.moviedetail.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.testapplication.MoviesApplication;
import com.testapplication.moviedetail.dagger.DaggerMovieDetailComponent;
import com.testapplication.moviedetail.dagger.MovieDetailModule;
import com.testapplication.moviedetail.presenter.MovieDetailPresenter;
import com.testapplication.moviesapi.R;
import com.testapplication.moviesplaying.model.Movie;

import java.util.List;

import javax.inject.Inject;


public class MovieDetailActivity extends AppCompatActivity implements MovieDetailPresenter.View {

    private static final String EXTRA_MOVIE = "extraMovie";

    @Inject
    MovieDetailPresenter presenter;

    private CollectionsAdapter collectionsAdapter;

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
        initViews();
    }

    private void initViews() {
        collectionsAdapter = new CollectionsAdapter(presenter);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.detail_collection);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(collectionsAdapter);
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

    @Override
    public void showCollectionDetails(Movie movie) {
        startActivity(MovieDetailActivity.createIntent(this, movie));
        finish();
    }

    @Override
    public void showMoviesCollection(List<Movie> movies) {
        (findViewById(R.id.detail_collections_title)).setVisibility(View.VISIBLE);
        collectionsAdapter.setData(movies);
        collectionsAdapter.notifyDataSetChanged();
    }
}
