package com.testapplication.moviesapi;


import com.testapplication.moviedetail.presenter.MovieDetailPresenter;
import com.testapplication.moviedetail.presenter.MovieDetailPresenterImpl;
import com.testapplication.base.services.MoviesService;
import com.testapplication.moviesplaying.model.Movie;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieDetailsPresenterTest {

    @Mock
    MovieDetailPresenter.View view;
    @Mock
    MoviesService moviesService;

    @Test
    public void test_onAttach_showMovieDetails() {
        //given
        MovieDetailPresenter presenter = createDefaultSUT();
        Movie movie = new Movie();
        movie.setId("123");
        when(moviesService.getInfo("123")).thenReturn(Observable.just(movie));
        presenter.onInitialise(movie);

        //when
        presenter.attachView(view);

        //then
        verify(view).showMovieDetails(movie);
    }

    @Test
    public void test_onMovieSelected_showCollectionDetails() {
        //given
        MovieDetailPresenter presenter = createDefaultSUT();
        List<Movie> movies = new ArrayList<>();
        Movie movie = new Movie();
        movie.setId("123");
        movies.add(movie);
        when(moviesService.getInfo("123")).thenReturn(Observable.just(movie));
        presenter.onInitialise(movie);
        presenter.attachView(view);

        //when
        presenter.onMovieSelected(movie);

        //then
        verify(view).showCollectionDetails(movie);
    }

    private MovieDetailPresenter createDefaultSUT() {
        return new MovieDetailPresenterImpl(moviesService, Schedulers.immediate(), Schedulers.immediate());
    }

}
