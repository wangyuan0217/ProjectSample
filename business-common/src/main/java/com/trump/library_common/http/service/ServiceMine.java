package com.trump.library_common.http.service;

import com.trump.library_common.http.response.BaseResponse;

import io.reactivex.Flowable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Description: 网络请求API
 */
public interface ServiceMine {

    @POST("VT/chat/loginCallback")
    Flowable<BaseResponse<String>> loginCallback(@Query("account") String account,
                                                 @Query("token") String token,
                                                 @Query("device") String device,
                                                 @Query("deviceNum") String deviceNum);
}
