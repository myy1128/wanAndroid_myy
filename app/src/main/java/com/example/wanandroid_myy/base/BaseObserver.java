package com.example.wanandroid_myy.base;

import com.example.wanandroid_myy.http.ApiException;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class BaseObserver<T> implements Observer<T> {
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onSubscribe(Disposable d) {
        compositeDisposable.add(d);
    }

    @Override
    public void onNext(T value) {
        onSuccess(value);
    }

    @Override
    public void onError(Throwable e) {
        //网络请求成功
        if (e instanceof ApiException) {
            ApiException apiException= (ApiException) e;
            int errorCode = apiException.getErrorCode();
            switch (errorCode){
                case 1:

            }
            error(apiException.getErrorMsg());
            //网络请求失败
        }else if(e instanceof HttpException){
            error(e.getMessage());
        }
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    @Override
    public void onComplete() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    public abstract  void onSuccess(T data);

    public abstract void error(String  error);
}
