package com.trump.main.splash;

import android.os.Handler;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.trump.library_common.router.ActivityJumper;
import com.trump.library_common.ui.base.activity.BaseActivity;
import com.trump.main.databinding.ActivitySplashBinding;

@Route(path = "/main/splash")
public class SplashActivity extends BaseActivity<ActivitySplashBinding> {

    @Override
    protected boolean applyFullScreen() {
        return true;
    }

    @Override
    protected void initData() {
        new Handler().postDelayed(ActivityJumper::toMain, 1500);
    }
}
