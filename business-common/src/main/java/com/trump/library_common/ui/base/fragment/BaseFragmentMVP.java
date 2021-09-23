package com.trump.library_common.ui.base.fragment;

import android.content.Context;

import androidx.viewbinding.ViewBinding;

import com.trump.library_common.ui.base.view.IBasePresenter;
import com.trump.library_common.ui.base.view.IBaseView;

/**
 * @author 王元_Trump
 * @time 2020/03/19 15:39
 * @desc
 */
public abstract class BaseFragmentMVP<VB extends ViewBinding, V extends IBaseView, P extends IBasePresenter<V>> extends BaseFragment<VB> {

    /**
     * Presenter
     */
    private P presenter;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        presenter = initPresenter();
        if (presenter != null) {
            presenter.setContext(mActivity);
            presenter.attachView((V) this);
        }
    }

    /**
     * 初始化Presenter
     *
     * @return PP
     */
    protected abstract P initPresenter();

    /**
     * 获取Presenter
     *
     * @return
     */
    protected P getPresenter() {
        return presenter;
    }

}