package com.trump.user.userinfo.presenter;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.trump.library_common.http.provider.UserProvider;
import com.trump.library_common.http.rxJava.RxConsumer;
import com.trump.library_common.http.rxJava.RxThrowableConsumer;
import com.trump.library_common.model.response.UserInfo;
import com.trump.library_common.rx.RxScheduler;
import com.trump.library_common.ui.base.presenter.BasePresenter;
import com.trump.user.userinfo.contract.UserInfoContract;

import io.reactivex.disposables.Disposable;

/**
 * @author 王元_Trump
 * @time 2020/05/25 17:08
 * @desc
 */
public class UserInfoPresenter extends BasePresenter<UserInfoContract.View> implements UserInfoContract.Presenter {

    @Autowired
    UserProvider mUserProvider;

    public UserInfoPresenter() {
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getUserInfo(String userId) {
        Disposable disposable = mUserProvider.getUserInfo(userId)
                .compose(RxScheduler.inIoMainLoading(getContext()))
                .subscribe(new RxConsumer<UserInfo>() {

                    @Override
                    public void _accept(UserInfo userInfo) {
                        getMvpView().showUserInfo(userInfo);
                    }
                }, new RxThrowableConsumer());
        addTask(disposable);
    }
}
