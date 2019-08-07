package com.example.wanandroid_myy.app;

import android.app.Application;

public class MyApp extends Application {
    public static MyApp myApp;

    public static MyApp getInstance() {
        return myApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
    }
}
