package com.trump.main;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.trump.library_common.ui.base.activity.BaseActivity;
import com.trump.library_common.ui.base.view.IBasePresenter;

@Route(path = "/main/fragment_activity")
public class MainFragmentActivity extends BaseActivity {

    protected int getLayoutId() {
        return R.layout.main_activity_main_fragment;
    }

    @Override
    protected void initData() {

    }
}
