package com.trump.mine;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.trump.library_common.ui.base.activity.BaseActivity;
import com.trump.library_common.ui.base.view.IBasePresenter;

@Route(path = "/mine/fragment_activity")
public class MineFragmentActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_mine_fragment;
    }

    @Override
    protected IBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }
}
