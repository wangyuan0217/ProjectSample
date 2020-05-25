package com.trump.library_common.http.service;

import com.trump.library_common.http.response.BaseResponse;
import com.trump.library_common.model.response.UserInfo;

import io.reactivex.Flowable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Description: 网络请求API
 */
public interface ServiceUser {

    @POST("user/info")
    Flowable<BaseResponse<UserInfo>> getUserInfo(@Query("userId") String userId);
}
