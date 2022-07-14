package com.trump.sample.test.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.trump.library_common.ui.base.activity.BaseActivityMVP;
import com.trump.sample.databinding.ActivityTestBinding;
import com.trump.sample.test.contract.TestContract;
import com.trump.sample.test.presenter.TestPresenter;

@Route(path = "/app/test")
public class TestActivity extends BaseActivityMVP<ActivityTestBinding, TestContract.View, TestContract.Presenter> implements TestContract.View {

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

        int a = 10_000;


    }

}
