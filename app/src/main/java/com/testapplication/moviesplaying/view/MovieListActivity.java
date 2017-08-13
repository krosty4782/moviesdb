package com.testapplication.moviesplaying.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.testapplication.MoviesApplication;
import com.testapplication.moviedetail.view.MovieDetailActivity;
import com.testapplication.moviesapi.R;
import com.testapplication.moviesplaying.dagger.DaggerMovieListComponent;
import com.testapplication.moviesplaying.dagger.MovieListModule;
import com.testapplication.moviesplaying.model.Movie;
import com.testapplication.moviesplaying.presenter.MovieListPresenter;

import java.util.List;

import javax.inject.Inject;


public class MovieListActivity extends AppCompatActivity implements MovieListPresenter.View {

    private MoviesAdapter moviesAdapter;

    @Inject
    MovieListPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        DaggerMovieListComponent.builder()
                .applicationComponent(((MoviesApplication) getApplication()).getApplicationComponent())
                .movieListModule(new MovieListModule())
                .build()
                .inject(this);

        initViews();
    }

    private void initViews() {
        moviesAdapter = new MoviesAdapter(presenter);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(moviesAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    public void showMovieList(List<Movie> movieList) {
        moviesAdapter.setData(movieList);
    }

    @Override
    public void showMovieDetail(Movie movie) {
        startActivity(MovieDetailActivity.createIntent(this, movie));
    }

    @Override
    protected void onDestroy() {
        if (isFinishing()) {
            presenter.destroy();
        }
        super.onDestroy();
    }
}
