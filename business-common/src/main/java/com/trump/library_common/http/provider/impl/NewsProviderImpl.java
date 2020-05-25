package com.trump.library_common.http.provider.impl;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.trump.library_common.http.provider.NewsProvider;
import com.trump.library_common.http.response.BaseResponse;
import com.trump.library_common.http.retrofit.RetrofitManager;
import com.trump.library_common.http.service.ServiceNews;
import com.trump.library_common.model.response.NewsType;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @author 王元_Trump
 * @desc 资讯相关网络请求
 */
@Route(path = "/http/news", name = "资讯新闻模块")
public class NewsProviderImpl implements NewsProvider {

    private ServiceNews getApi() {
        return RetrofitManager.getRetrofitClient().create(ServiceNews.class);
    }

    @Override
    public void init(Context context) {

    }

    @Override
    public Flowable<BaseResponse<List<NewsType>>> getNewsType() {
        return getApi().getNewsType();
    }

}
