package com.trump.library_common.router;

import android.content.Intent;

import com.alibaba.android.arouter.launcher.ARouter;
import com.trump.library_common.ui.base.fragment.BaseFragment;

/**
 * @author Trump
 * @time 2019/11/23 0023 15:23
 * @desc 页面跳转管理类--不同module的一级路径必须不同，否则会导致一个moudle中的一级路径失效！！！
 * module/xx/xx/xx
 */
public class ActivityJumper {

    public static BaseFragment getFragmentHome() {
        return (BaseFragment) ARouter.getInstance().build("/home/fragment").navigation();
    }

    public static BaseFragment getFragmentMine() {
        return (BaseFragment) ARouter.getInstance().build("/mine/fragment").navigation();
    }

    //app主页
    public static void toMain() {
        ARouter.getInstance().build("/app/main")
                .withFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .navigation();
    }

    public static void toLogin() {
//        ARouter.getInstance().build("/app/login")
//                .withFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK)
//                .navigation();
    }

    //个人信息
    public static void toUserInfo() {
        ARouter.getInstance().build("/user/info")
                .navigation();
    }

}
