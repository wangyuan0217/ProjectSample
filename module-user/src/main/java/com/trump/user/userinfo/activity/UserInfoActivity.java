package com.trump.user.userinfo.activity;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.trump.library_common.model.response.UserInfo;
import com.trump.library_common.ui.base.activity.BaseActivity;
import com.trump.user.R;
import com.trump.user.userinfo.contract.UserInfoContract;
import com.trump.user.userinfo.presenter.UserInfoPresenter;

@Route(path = "/user/info")
public class UserInfoActivity extends BaseActivity<UserInfoContract.View, UserInfoContract.Presenter>
        implements UserInfoContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.user_activity_user_info;
    }

    @Override
    protected UserInfoContract.Presenter initPresenter() {
        return new UserInfoPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void showUserInfo(UserInfo userInfo) {

    }
}
