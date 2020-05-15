package com.trump.sample;

import com.alibaba.android.arouter.launcher.ARouter;
import com.socks.library.KLog;
import com.tencent.smtt.sdk.QbSdk;
import com.trump.library_common.BaseApplication;

public class App extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        initARouter();

        initInThread();
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


    private void initInThread() {
        new Thread(() -> {
            //初始化TBS浏览服务X5内核
            //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
            QbSdk.setDownloadWithoutWifi(true);//非wifi条件下允许下载X5内核
            QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

                @Override
                public void onViewInitFinished(boolean arg0) {
                    //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                    KLog.d("X5WebView onViewInitFinished is " + arg0);
                }

                @Override
                public void onCoreInitFinished() {
                }
            };
            //x5内核初始化接口
            QbSdk.initX5Environment(getApplicationContext(), cb);

        }).start();
    }
}
