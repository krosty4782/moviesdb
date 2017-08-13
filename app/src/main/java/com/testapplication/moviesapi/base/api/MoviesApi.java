package com.testapplication.moviesapi.base.api;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface MoviesApi {

    @GET("/movie/now_playing")
    Observable<ResultsApiModel> getNowPlaying();

    @GET("/movie/{id}")
    Observable<MovieApiModel> getMovieInformation(@Path("id") String movieId);
}
