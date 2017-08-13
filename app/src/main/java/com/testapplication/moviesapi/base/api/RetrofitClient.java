package com.testapplication.moviesapi.base.api;

public interface RetrofitClient {

    <T> T api(Class<T> service);

}