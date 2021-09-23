package com.trump.library_common.http.retrofit;

import com.trump.library_common.config.UrlConstant;
import com.trump.library_common.http.okhttp.OKHttpFactory;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private volatile static Retrofit sRetrofit;

    public static Retrofit getRetrofitClient() {
        if (sRetrofit == null) {
            synchronized (RetrofitManager.class) {
                if (sRetrofit == null) {
                    sRetrofit = new Retrofit.Builder()
                            //baseUrl
                            .baseUrl(UrlConstant.API_HOST)
                            //设置OKHttpClient
                            .client(OKHttpFactory.INSTANCE.getOkHttpClient())
                            //fix Retrofit以Mutipart上传参数时，String参数会多一对双引号
                            //.addConverterFactory(ScalarsConverterFactory.create())
                            //gson转化器
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                }
            }
        }
        return sRetrofit;
    }

}
