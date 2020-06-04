package com.trump.library_common.app;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.socks.library.KLog;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.smtt.sdk.QbSdk;
import com.trump.config.CommonConfig;

import io.reactivex.plugins.RxJavaPlugins;

/**
 * @author 王元_Trump
 * @time 2020/05/25 14:46
 * @desc 处理Application里面耗时的初始化话-
 */
public class InitializeService extends IntentService {

    private static final String ACTION_INIT = "initApplication";

    public InitializeService() {
        super("InitializeService");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT.equals(action)) {
                publicApplicationInit();

                modulesApplicationInit();
            }
        }
    }

    private void publicApplicationInit() {

        KLog.d("function - publicApplicationInit");

        initBugly();

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
}