package com.example.quiz.core;

public interface IBaseCallback<T> {

    void onSuccess(T result);

    void onFailure(Exception exception);
}
