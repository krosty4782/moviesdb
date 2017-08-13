package com.testapplication.moviesapi.base.dagger;

import com.google.gson.GsonBuilder;
import com.testapplication.moviesapi.base.api.MovieDBClient;
import com.testapplication.moviesapi.base.api.MoviesApi;
import com.testapplication.moviesapi.base.api.RequestInterceptor;
import com.testapplication.moviesapi.base.api.RetrofitClient;
import com.testapplication.moviesapi.base.services.MoviesService;
import com.testapplication.moviesplaying.services.SearchApiModelToSearchConverter;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    //TODO api key would probably go in a configuration file for okHttp, harcoded here for time purposes
    private static final String API_KEY = "6808da72";

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
    public SearchApiModelToSearchConverter provideSearchApiModelToSearchConverter() {
        return new SearchApiModelToSearchConverter();
    }

    @Provides
    @ApplicationScope
    public MoviesService provideMoviesService(MoviesApi moviesApi, SearchApiModelToSearchConverter converter) {
        return new MoviesService(moviesApi, converter);
    }
}
