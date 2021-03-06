package com.testapplication.base.presenter;

public interface Presenter<T extends PresenterView> {

    void attachView(T presenterView);

    void detachView();

    void destroy();
}
