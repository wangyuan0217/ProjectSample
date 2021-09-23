package com.trump.library_common.config;

import com.trump.library_common.BuildConfig;

public class CommonConfig {

    public static String[] MODULE_APPIDS = {BuildConfig.ID_MAIN + ".MainApplication",
            BuildConfig.ID_MINE + ".MineApplication"
    };

    //组件化标识
    public static boolean PACKAGE_MODULE = Boolean.parseBoolean(BuildConfig.isModulePackage);

    //日志开关
    public static boolean LOG_ENABLE = Boolean.parseBoolean(BuildConfig.LOG_ENABLE);


    //DB配置
    public static int DB_VERSION = Integer.parseInt(BuildConfig.DB_VERSION);
    public static String DB_NAME = BuildConfig.DB_NAME;

    public static String BUGLY_ID = BuildConfig.BUGLY_APPID;
}
