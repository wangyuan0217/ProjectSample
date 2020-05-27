package com.trump.library_common.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.socks.library.KLog;
import com.trump.config.CommonConfig;
import com.trump.library_common.BuildConfig;
import com.trump.library_common.utils.AppManager;

import androidx.annotation.NonNull;

/**
 * @author 王元_Trump
 * @time 2020/05/21 11:43
 * @desc
 */
public class BaseApplication extends Application {

    private static BaseApplication instance;

    public static BaseApplication getContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        //是否开启打印日志
        KLog.init(CommonConfig.LOG_ENABLE);

        initLifecycle();

        initARouter();

        //在子线程中完成其他初始化
        InitializeService.start(this);
    }

    /**
     * 初始化路由
     */
    private void initARouter() {
        if (BuildConfig.DEBUG) {
            // 打印日志
            ARouter.openLog();
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
        }
        ARouter.init(instance);
    }

    /**
     * activity生命周期管理监听
     */
    private void initLifecycle() {
        registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {

            @Override
            public void onActivityCreated(@NonNull Activity activity, Bundle savedInstanceState) {
                AppManager.getAppManager().addActivity(activity);
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                AppManager.getAppManager().removeActivity(activity);
            }
        });
    }
}