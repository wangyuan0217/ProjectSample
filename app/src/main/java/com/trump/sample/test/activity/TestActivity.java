package com.trump.sample.test.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.trump.library_common.ui.base.activity.BaseActivity;
import com.trump.sample.R;
import com.trump.sample.test.contract.TestContract;
import com.trump.sample.test.presenter.TestPresenter;

@Route(path = "/app/test")
public class TestActivity extends BaseActivity<TestContract.View, TestContract.Presenter> implements TestContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected TestContract.Presenter initPresenter() {
        return new TestPresenter();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

    }

    @Override
    protected void initData() {
    }

}
