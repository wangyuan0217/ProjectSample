package com.trump.library_common.router;

import com.alibaba.android.arouter.launcher.ARouter;
import com.trump.library_common.ui.base.fragment.BaseFragment;

/**
 * @author Trump
 * @time 2019/11/23 0023 15:23
 * @desc 页面跳转管理类--不同module的一级路径必须不同，否则会导致一个moudle中的一级路径失效！！！
 * module/xx/xx/xx
 */
public class ActivityJumper {

    public static BaseFragment getFragmentMain() {
        return (BaseFragment) ARouter.getInstance().build("/main/fragment").navigation();
    }

    public static BaseFragment getFragmentMine() {
        return (BaseFragment) ARouter.getInstance().build("/mine/fragment").navigation();
    }

    //开屏页
    public static void toSplash() {
        ARouter.getInstance().build("/app/splash")
                .navigation();
    }

    //app主页
    public static void toMain() {
        ARouter.getInstance().build("/app/main")
                .navigation();
    }

    //个人信息
    public static void toUserInfo() {
        ARouter.getInstance().build("/user/info")
                .navigation();
    }

}
