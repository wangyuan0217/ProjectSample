package com.trump.home;

import android.app.Application;

import com.socks.library.KLog;
import com.trump.library_common.app.IComponentApplication;

/**
 * @author 王元_Trump
 * @time 2020/05/25 14:54
 * @desc
 */
public class HomeApplication implements IComponentApplication {
    @Override
    public void init(Application application) {
        KLog.d("function - HomeApplication init");
    }
}
