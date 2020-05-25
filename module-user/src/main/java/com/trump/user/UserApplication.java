package com.trump.user;

import android.app.Application;

import com.socks.library.KLog;
import com.trump.library_common.app.IComponentApplication;

/**
 * @author 王元_Trump
 * @time 2020/05/25 16:59
 * @desc
 */
public class UserApplication implements IComponentApplication {
    @Override
    public void init(Application application) {
        KLog.d("function - UserApplication init");
    }
}
