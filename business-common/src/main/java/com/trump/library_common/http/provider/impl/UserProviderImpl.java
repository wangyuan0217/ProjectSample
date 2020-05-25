package com.trump.library_common.http.provider.impl;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.trump.library_common.http.provider.NewsProvider;
import com.trump.library_common.http.provider.UserProvider;
import com.trump.library_common.http.response.BaseResponse;
import com.trump.library_common.http.retrofit.RetrofitManager;
import com.trump.library_common.http.service.ServiceNews;
import com.trump.library_common.http.service.ServiceUser;
import com.trump.library_common.model.response.NewsType;
import com.trump.library_common.model.response.UserInfo;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @author 王元_Trump
 * @desc 用户相关网络请求
 */
@Route(path = "/http/user", name = "用户模块")
public class UserProviderImpl implements UserProvider {

    private ServiceUser getApi() {
        return RetrofitManager.getRetrofitClient().create(ServiceUser.class);
    }

    @Override
    public void init(Context context) {

    }

    @Override
    public Flowable<BaseResponse<UserInfo>> getUserInfo(String userId) {
        return getApi().getUserInfo(userId);
    }

}
