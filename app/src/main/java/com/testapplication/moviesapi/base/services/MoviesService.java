package com.testapplication.moviesapi.base.services;

import android.support.annotation.NonNull;

import com.testapplication.moviesapi.base.api.MoviesApi;
import com.testapplication.moviesapi.base.api.ResultsApiModel;
import com.testapplication.moviesplaying.model.Movie;
import com.testapplication.moviesplaying.services.MovieApiModelToMovieConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;

public class MoviesService {

    private final MoviesApi moviesApi;
    private MovieApiModelToMovieConverter searchApiModelToSearchConverter;
    // In memory cache. In prod we should have some update policy that will define when do we want
    // to update local cache against network api request.
    private List<Movie> nowPlayingCache = new ArrayList<>();
    private Map<String, Movie> movieInfoCache = new HashMap<>();
    private Map<String, List<Movie>> movieCollectionCache = new HashMap<>();

    public MoviesService(@NonNull MoviesApi moviesApi, @NonNull MovieApiModelToMovieConverter searchApiModelToSearchConverter) {
        this.moviesApi = moviesApi;
        this.searchApiModelToSearchConverter = searchApiModelToSearchConverter;
    }

    public Observable<List<Movie>> nowPlaying() {
        if (!nowPlayingCache.isEmpty()) {
            return Observable.just(nowPlayingCache);
        }

        return moviesApi.getNowPlaying()
                .flatMapIterable(ResultsApiModel::getResults)
                .map(searchApiModel -> searchApiModelToSearchConverter.convertSearchApiModelToSearchModel(searchApiModel))
                .doOnNext(movies -> nowPlayingCache.add(movies))
                .toList();
    }

    public Observable<Movie> getInfo(String movieId) {
        if (movieInfoCache.containsKey(movieId)) {
            return Observable.just(movieInfoCache.get(movieId));
        }
        return moviesApi.getMovieInformation(movieId)
                .map(movieApiModel -> searchApiModelToSearchConverter.convertSearchApiModelToSearchModel(movieApiModel))
                .doOnNext(movie -> movieInfoCache.put(movieId, movie));
    }


    public Observable<List<Movie>> getMoviesCollection(String collectionId) {
        if (movieCollectionCache.containsKey(collectionId)) {
            return Observable.just(movieCollectionCache.get(collectionId));
        }
        return moviesApi.getMovieCollection(collectionId)
                .flatMapIterable(collectionApiModel -> collectionApiModel.getMovies())
                .map(movieApiModel -> searchApiModelToSearchConverter.convertSearchApiModelToSearchModel(movieApiModel))
                .toList()
                .doOnNext(movie -> movieCollectionCache.put(collectionId, movie));
    }
}
