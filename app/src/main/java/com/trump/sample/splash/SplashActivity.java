package com.trump.sample.splash;

import android.os.Handler;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.trump.library_common.router.ActivityJumper;
import com.trump.library_common.ui.base.activity.BaseActivity;
import com.trump.library_common.ui.base.view.IBasePresenter;
import com.trump.library_common.ui.base.view.IBaseView;
import com.trump.sample.R;
import com.trump.sample.databinding.ActivitySplashBinding;

@Route(path = "/app/splash")
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
