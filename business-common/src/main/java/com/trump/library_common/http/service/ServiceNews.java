package com.trump.library_common.http.service;

import com.trump.library_common.http.response.BaseResponse;
import com.trump.model.response.NewsType;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * @author 王元_Trump
 * @time 2020/3/20 0020 15:36
 * @desc 资讯
 */
public interface ServiceNews {

    /**
     * 资讯类别
     *
     * @return
     */
    @GET("news/types")
    Flowable<BaseResponse<List<NewsType>>> getNewsType();
}
