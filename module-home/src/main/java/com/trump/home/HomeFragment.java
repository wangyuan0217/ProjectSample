package com.trump.home;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jakewharton.rxbinding2.view.RxView;
import com.socks.library.KLog;
import com.trump.home.databinding.HomeFragmentHomeBinding;
import com.trump.library_common.http.provider.ApiHelper;
import com.trump.library_common.http.rxJava.RxConsumer;
import com.trump.library_common.http.rxJava.RxThrowableConsumer;
import com.trump.library_common.model.response.UserInfo;
import com.trump.library_common.router.ActivityJumper;
import com.trump.library_common.rx.RxScheduler;
import com.trump.library_common.ui.base.fragment.BaseFragment;

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
    protected void initListener() {
        Disposable disposable = RxView.clicks(getBinding().text)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(o -> ActivityJumper.toUserInfo());

        addDisposable(ApiHelper.getApi().getUserInfo("1")
                .compose(RxScheduler.inIoMainLoading(mContext))
                .subscribe(new RxConsumer<>() {

                    @Override
                    public void _accept(UserInfo userInfo) {

                    }
                }, new RxThrowableConsumer()));
    }
}
