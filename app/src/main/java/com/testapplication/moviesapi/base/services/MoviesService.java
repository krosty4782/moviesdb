package com.testapplication.moviesapi.base.services;

import android.support.annotation.NonNull;

import com.testapplication.moviesapi.base.api.MoviesApi;
import com.testapplication.moviesapi.base.api.ResultsApiModel;
import com.testapplication.moviesplaying.services.SearchApiModelToSearchConverter;
import com.testapplication.moviesplaying.model.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;

public class MoviesService {

    private final MoviesApi moviesApi;
    private SearchApiModelToSearchConverter searchApiModelToSearchConverter;
    // In memory cache. In prod we should have some update policy that will define when do we want
    // to update local cache against network api request.
    private List<Movie> nowPlayingCache = new ArrayList<>();
    private Map<String, Movie> movieInfoCache = new HashMap<>();

    public MoviesService(@NonNull MoviesApi moviesApi, @NonNull SearchApiModelToSearchConverter searchApiModelToSearchConverter) {
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
}
