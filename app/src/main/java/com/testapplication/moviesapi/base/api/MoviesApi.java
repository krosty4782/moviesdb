package com.testapplication.moviesapi.base.api;

import com.testapplication.moviedetail.model.CollectionApiModel;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface MoviesApi {

    @GET("movie/now_playing")
    Observable<ResultsApiModel> getNowPlaying();

    @GET("movie/{id}")
    Observable<MovieApiModel> getMovieInformation(@Path("id") String movieId);

    //This should be declared in a CollectionApi file, will do it here for time purposes.
    @GET("collection/{id}")
    Observable<CollectionApiModel> getMovieCollection(@Path("id") String collectionId);
}
