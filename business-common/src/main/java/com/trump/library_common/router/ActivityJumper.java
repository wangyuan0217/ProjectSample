package com.trump.library_common.router;

import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @author Trump
 * @time 2019/11/23 0023 15:23
 * @desc 页面跳转管理类--不同module的一级路径必须不同，否则会导致一个moudle中的一级路径失效！！！
 * module/xx/xx/xx
 */
public class ActivityJumper {

    /**
     * 公共的webviewactivity
     */
    public static void toPersonnalInfo(Context context) {
        ARouter.getInstance().build("/mine/personnal")
                .navigation(context);
    }

}
