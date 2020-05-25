package com.trump.library_common.http.provider;


import com.alibaba.android.arouter.facade.template.IProvider;
import com.trump.library_common.http.response.BaseResponse;
import com.trump.library_common.model.response.UserInfo;

import io.reactivex.Flowable;

/**
 * @author 王元_Trump
 * @desc 用户相关网络请求
 */
//tip by arouter
//Declaration interface, other components get the service instance through the interface
public interface UserProvider extends IProvider {

    Flowable<BaseResponse<UserInfo>> getUserInfo(String userId);
}
