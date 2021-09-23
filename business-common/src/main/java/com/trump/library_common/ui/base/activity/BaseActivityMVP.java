package com.trump.library_common.ui.base.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.trump.library_common.ui.base.view.IBasePresenter;
import com.trump.library_common.ui.base.view.IBaseView;

public abstract class BaseActivityMVP<VB extends ViewBinding, V extends IBaseView, P extends IBasePresenter<V>> extends BaseActivity<VB> {

    /**
     * Presenter
     */
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.setContext(this);
            mPresenter.attachView((V) this);
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }


    /**
     * 初始化Presenter
     *
     * @return P
     */
    protected abstract P initPresenter();

}