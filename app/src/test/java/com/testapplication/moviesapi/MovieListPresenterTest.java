package com.testapplication.moviesapi;

import com.testapplication.base.services.MoviesService;
import com.testapplication.moviesplaying.model.Movie;
import com.testapplication.moviesplaying.presenter.MovieListPresenter;
import com.testapplication.moviesplaying.presenter.MovieListPresenterImpl;

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
public class MovieListPresenterTest {

    @Mock
    MoviesService moviesService;
    @Mock
    MovieListPresenter.View view;


    @Test
    public void test_onAttach_showMovieList() {
        //given
        MovieListPresenter presenter = createDefaultSUT();
        List<Movie> movies = new ArrayList<>();
        Movie movie = new Movie();
        movie.setId("123");
        movies.add(movie);
        when(moviesService.nowPlaying()).thenReturn(Observable.just(movies));

        //when
        presenter.attachView(view);

        //then
        verify(view).showMovieList(movies);
    }

    @Test
    public void test_onMovieSelected_showMovieList() {
        //given
        MovieListPresenter presenter = createDefaultSUT();
        List<Movie> movies = new ArrayList<>();
        Movie movie = new Movie();
        movie.setId("123");
        movies.add(movie);
        when(moviesService.nowPlaying()).thenReturn(Observable.just(movies));
        presenter.attachView(view);

        //when
        presenter.onMovieSelected(movie);

        //then
        verify(view).showMovieDetail(movie);
    }

    private MovieListPresenter createDefaultSUT() {
        return new MovieListPresenterImpl(moviesService, Schedulers.immediate(), Schedulers.immediate());
    }

}