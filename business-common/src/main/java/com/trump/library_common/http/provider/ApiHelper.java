package com.trump.library_common.http.provider;

import com.trump.library_common.http.retrofit.RetrofitManager;
import com.trump.library_common.http.service.ServiceUser;

public class ApiHelper {

    private volatile static ServiceUser mServiceUser;

    public static ServiceUser getApi() {
        if (mServiceUser == null) {
            synchronized (RetrofitManager.class) {
                if (mServiceUser == null) {
                    mServiceUser = RetrofitManager.getRetrofitClient().create(ServiceUser.class);
                }
            }
        }
        return mServiceUser;
    }
}
