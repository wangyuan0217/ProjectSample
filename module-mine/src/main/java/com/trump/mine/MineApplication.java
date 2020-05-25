package com.trump.mine;

import android.app.Application;

import com.socks.library.KLog;
import com.trump.library_common.app.IComponentApplication;

/**
 * @author 王元_Trump
 * @time 2020/05/25 14:56
 * @desc
 */
public class MineApplication implements IComponentApplication {
    @Override
    public void init(Application application) {
        KLog.d("function - MineApplication init");
    }
}
