package com.trump.home;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jakewharton.rxbinding2.view.RxView;
import com.trump.home.databinding.HomeFragmentHomeBinding;
import com.trump.library_common.router.ActivityJumper;
import com.trump.library_common.ui.base.fragment.BaseFragment;
import com.trump.library_common.utils.ToastUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.Disposable;

/**
 * @author 王元_Trump
 * @time 2020/03/26 16:42
 * @desc
 */
@Route(path = "/home/fragment")
public class HomeFragment extends BaseFragment<HomeFragmentHomeBinding> {

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        getBinding().text.setText(getText("trump"));
        ToastUtil.show(getText("trump"));
    }

    @Override
    protected void initListener() {
        Disposable disposable = RxView.clicks(getBinding().text)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(o -> ActivityJumper.toUserInfo());
    }

    public String getText(String msg) {
        return "This is the text : " + msg;
    }
}
