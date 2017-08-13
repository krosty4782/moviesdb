package com.testapplication.base.api;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {

    private static final String APIKEY = "api_key";
    private String apiKey;

    public RequestInterceptor(String apiKey) {

        this.apiKey = apiKey;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request originalRequest = chain.request();
        HttpUrl url = originalRequest.url().newBuilder()
                .addQueryParameter(APIKEY, apiKey)
                .build();

        Request request = originalRequest.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}
