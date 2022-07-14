package com.trump.home;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.socks.library.KLog;
import com.trump.home.databinding.HomeActivityHomeFragmentBinding;
import com.trump.library_common.http.provider.ApiHelper;
import com.trump.library_common.http.rxJava.RxConsumer;
import com.trump.library_common.http.rxJava.RxThrowableConsumer;
import com.trump.library_common.model.response.UserInfo;
import com.trump.library_common.rx.RxScheduler;
import com.trump.library_common.ui.base.activity.BaseActivity;

@Route(path = "/home/fragment_activity")
public class HomeFragmentActivity extends BaseActivity<HomeActivityHomeFragmentBinding> {

    @Override
    protected void initData() {
    }
}