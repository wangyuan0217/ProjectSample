package com.trump.library_common.http.provider;


import com.alibaba.android.arouter.facade.template.IProvider;
import com.trump.library_common.http.response.BaseResponse;

import io.reactivex.Flowable;

/**
 * Description:账户相关网络请求
 */
public interface MineServiceProvider extends IProvider {

    /**
     * 统一登录成功后调用 用户登录
     *
     * @param account
     * @param token
     * @param device
     * @return
     */
    Flowable<BaseResponse<String>> loginCallback(String account, String token, String device);

}
