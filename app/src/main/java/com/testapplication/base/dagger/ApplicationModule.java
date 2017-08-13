package com.testapplication.base.dagger;

import com.google.gson.GsonBuilder;
import com.testapplication.base.api.MovieDBClient;
import com.testapplication.base.api.MoviesApi;
import com.testapplication.base.api.RequestInterceptor;
import com.testapplication.base.api.RetrofitClient;
import com.testapplication.base.services.MoviesService;
import com.testapplication.moviesplaying.services.MovieApiModelToMovieConverter;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    //TODO api key would probably go in a configuration file for okHttp, harcoded here for time purposes
    private static final String API_KEY = "70bea916230b169eb500a171c5264979";

    @Provides
    @ApplicationScope
    public Converter.Factory provideConverterFactory() {
        return GsonConverterFactory.create(new GsonBuilder().create());
    }

    @Provides
    @ApplicationScope
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder().addInterceptor(new RequestInterceptor(API_KEY)).build();
    }

    @Provides
    @ApplicationScope
    public RetrofitClient provideRetrofitClient(OkHttpClient okHttpClient,
                                                Converter.Factory converterFactory) {
        return new MovieDBClient(converterFactory, okHttpClient);
    }

    @Provides
    @ApplicationScope
    public MoviesApi provideSearchApi(RetrofitClient retrofitClient) {
        return retrofitClient.api(MoviesApi.class);
    }

    @Provides
    @ApplicationScope
    public MovieApiModelToMovieConverter provideSearchApiModelToSearchConverter() {
        return new MovieApiModelToMovieConverter();
    }

    @Provides
    @ApplicationScope
    public MoviesService provideMoviesService(MoviesApi moviesApi, MovieApiModelToMovieConverter converter) {
        return new MoviesService(moviesApi, converter);
    }
}
