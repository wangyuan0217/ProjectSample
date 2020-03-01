package com.trump.library_common.http.provider.impl;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.trump.library_common.http.provider.MineServiceProvider;
import com.trump.library_common.http.response.BaseResponse;
import com.trump.library_common.http.retrofit.RetrofitManager;

import io.reactivex.Flowable;

/**
 * Description:账户相关网络请求
 */
@Route(path = "/http/account", name = "账户模块")
public class MineServiceProviderImpl implements MineServiceProvider {

    @Override
    public void init(Context context) {

    }

    @Override
    public Flowable<BaseResponse<String>> loginCallback(String account, String token, String device) {
        return RetrofitManager.getServiceMine().loginCallback(account, token, device, "");
    }

}
