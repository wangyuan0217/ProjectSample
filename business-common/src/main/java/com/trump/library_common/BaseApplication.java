package com.trump.library_common;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.socks.library.KLog;
import com.trump.config.CommonConfig;
import com.trump.library_common.utils.AppManager;

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

        //是否开启打印日志
        KLog.init(CommonConfig.LOG_ENABLE);

        //RxJava全局异常处理（无网络闪退）
        RxJavaPlugins.setErrorHandler(throwable -> {
        });

        initLifecycle();
    }

    /**
     * activity生命周期管理监听
     */
    private void initLifecycle() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                AppManager.getAppManager().addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                AppManager.getAppManager().removeActivity(activity);
            }
        });
    }

    public static BaseApplication getContext() {
        return instance;
    }

}