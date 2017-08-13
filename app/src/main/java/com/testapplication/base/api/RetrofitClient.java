package com.testapplication.base.api;

public interface RetrofitClient {

    <T> T api(Class<T> service);

}