package com.trump.config;

public class CommonConfig {

    //日志开关
    public static boolean LOG_ENABLE = Boolean.valueOf(BuildConfig.LOG_ENABLE);


    //DB配置
    public static int DB_VERSION = Integer.parseInt(BuildConfig.DB_VERSION);
    public static String DB_NAME = BuildConfig.DB_NAME;


}
