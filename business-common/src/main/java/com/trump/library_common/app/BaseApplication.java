package com.trump.library_common.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;
import com.socks.library.KLog;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.smtt.sdk.QbSdk;
import com.trump.library_common.config.CommonConfig;
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
    protected void attachBaseContext(Context base) {
        //签名检验
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        //是否开启打印日志
        KLog.init(CommonConfig.LOG_ENABLE);

        publicApplicationInit();

        modulesApplicationInit();
    }

    private void publicApplicationInit() {

        KLog.d("function - publicApplicationInit");

        initBugly();

        initLifecycle();

        initARouter();

        //initX5webview();

        //RxJava全局异常处理（无网络闪退）
        RxJavaPlugins.setErrorHandler(throwable -> {
        });

        //初始化内存泄漏检测
//        LeakCanary.install(App.getInstance());

        //初始化过度绘制检测
//        BlockCanary.install(getApplicationContext(), new AppBlockCanaryContext()).start();
    }

    private void modulesApplicationInit() {
        for (String moduleImpl : CommonConfig.MODULE_APPIDS) {
            try {
                Class<?> clazz = Class.forName(moduleImpl);
                Object obj = clazz.newInstance();
                if (obj instanceof IComponentApplication) {
                    ((IComponentApplication) obj).init(BaseApplication.getContext());
                }
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    private void initX5webview() {
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
    }

    private void initBugly() {
        Context context = getApplicationContext();
        CrashReport.initCrashReport(context, CommonConfig.BUGLY_ID, CommonConfig.LOG_ENABLE);
    }

    /**
     * 初始化路由
     */
    private void initARouter() {
        if (CommonConfig.LOG_ENABLE) {
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