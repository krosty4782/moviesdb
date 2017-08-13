package com.testapplication.base.api;

import android.support.annotation.NonNull;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

public class MovieDBClient implements RetrofitClient {

    private static final String HOST_URL = "https://api.themoviedb.org/3/";
    private final Retrofit restAdapter;

    public MovieDBClient(@NonNull Converter.Factory converterFactory,
                         @NonNull OkHttpClient okHttpClient) {

        restAdapter = new Retrofit.Builder()
                .baseUrl(HOST_URL)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .validateEagerly(true)
                .build();
    }

    @Override
    public <T> T api(Class<T> service) {
        return restAdapter.create(service);
    }
}
