package com.trump.library_common;

import android.app.Application;

import io.reactivex.plugins.RxJavaPlugins;

public class BaseApplication extends Application {
    /**
     * Context
     */
    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        //RxJava全局异常处理（无网络闪退）
        RxJavaPlugins.setErrorHandler(throwable -> {
        });
    }

    public static BaseApplication getContext() {
        return instance;
    }

}