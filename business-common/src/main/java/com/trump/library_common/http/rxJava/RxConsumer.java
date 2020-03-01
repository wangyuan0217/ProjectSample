package com.trump.library_common.http.rxJava;


import androidx.annotation.NonNull;

import com.trump.library_common.http.response.BaseResponse;

import io.reactivex.functions.Consumer;

/**
 * Description: 统一订阅封装
 */
public abstract class RxConsumer<T> implements Consumer<BaseResponse<T>> {

    @Override
    public void accept(@NonNull BaseResponse<T> baseResponse) throws Exception {
//        //正常
//        if (HttpResponseConstant.RESPONSE_OK.equals(baseResponse.getCode())) {
//            _accept(baseResponse.getData());
//        }
//        //密码异常
//        else if (HttpResponseConstant.TOKEN_EXCEPTION.equals(baseResponse.getSub_code())) {
//            _handleMsg(baseResponse.getMessage(), baseResponse.getSub_code(), baseResponse.getSub_message());
//            EventBus.getDefault().post(new LoginOutEvent(false));
//        }
//        //token异常
//        else if (HttpResponseConstant.PASSWORD_EXCEPTION.equals(baseResponse.getSub_code())) {
//            _handleMsg(baseResponse.getMessage(), baseResponse.getSub_code(), baseResponse.getSub_message());
//            EventBus.getDefault().post(new LoginOutEvent(false));
//        } else {
//            _handleMsg(baseResponse.getMessage(), baseResponse.getSub_code(), baseResponse.getSub_message());
//        }
    }

    public abstract void _accept(T t);

    public abstract void _handleMsg(String message, String sub_code, String sub_message);
}
