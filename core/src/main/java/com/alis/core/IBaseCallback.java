package com.alis.core;

public interface IBaseCallback<T> {

    void onSuccess(T result);

    void onFailure(Exception exception);
}
