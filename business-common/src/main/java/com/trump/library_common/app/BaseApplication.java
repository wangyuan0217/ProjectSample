package com.trump.library_common.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.socks.library.KLog;
import com.tencent.smtt.sdk.QbSdk;
import com.trump.config.CommonConfig;
import com.trump.library_common.BuildConfig;
import com.trump.library_common.utils.AppManager;

import io.reactivex.plugins.RxJavaPlugins;

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

        publicApplicationInit();

        //Module类的APP初始化
        modulesApplicationInit();
    }

    private void publicApplicationInit() {
        //是否开启打印日志
        KLog.init(CommonConfig.LOG_ENABLE);

        //RxJava全局异常处理（无网络闪退）
        RxJavaPlugins.setErrorHandler(throwable -> {
        });

        initLifecycle();

        initARouter();

        //initX5webview();

        KLog.d("function - publicApplicationInit");
    }

    private void modulesApplicationInit() {
        for (String moduleImpl : CommonConfig.MODULE_APPIDS) {
            try {
                Class<?> clazz = Class.forName(moduleImpl);
                Object obj = clazz.newInstance();
                if (obj instanceof IComponentApplication) {
                    ((IComponentApplication) obj).init(this);
                }
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
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
        ARouter.init(this);
    }

    private void initX5webview() {
        new Thread(() -> {
            //初始化TBS浏览服务X5内核
            //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
            QbSdk.setDownloadWithoutWifi(true);//非wifi条件下允许下载X5内核
            QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

                @Override
                public void onViewInitFinished(boolean arg0) {
                    //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                }

                @Override
                public void onCoreInitFinished() {
                }
            };
            //x5内核初始化接口
            QbSdk.initX5Environment(getApplicationContext(), cb);

        }).start();
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

}