package com.trump.library_common.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.trump.config.LanguageConstant;

import java.util.Locale;

import androidx.annotation.RequiresApi;

/**
 * Description:语言工具类
 *
 * @Author: yaozhiheng
 * @Date: 2019/1/2
 */
public class LanguageUtil {

    private static final String SP_NAME = "sp_language";

    private static Locale systemCurrentLocal = Locale.CHINA;


    public static String getLanguageType() {
        return SPUtil.getInstance(SP_NAME).getString("LanguageType");
    }

    /**
     * //保存语言
     * LanguageUtil.saveLanguageType(this, String.valueOf(languageSetAdapter.getSelectLanguageType()));
     * //切换语言`
     * LanguageUtil.setApplicationLanguage(this);
     * //跳转首页
     * ActivityJumpManager.startMainActivityNewTask(this);
     *
     * @param languageType
     */
    public static void setLanguageType(String languageType) {
        SPUtil.getInstance(SP_NAME).put("LanguageType", languageType);
    }


    public static void applyLanguage(Context context, String newLanguageType) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale = getLanguageLocale(newLanguageType);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // apply locale
            configuration.setLocale(locale);

        } else {
            // updateConfiguration
            configuration.locale = locale;
            DisplayMetrics dm = resources.getDisplayMetrics();
            resources.updateConfiguration(configuration, dm);
        }
    }

    public static Context attachBaseContext(Context context) {
        String languageType = getLanguageType();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return createConfigurationResources(context, languageType);
        } else {
            applyLanguage(context, languageType);
            return context;
        }
    }

    public static void saveSystemCurrentLanguage(Context context) {
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = LocaleList.getDefault().get(0);
        } else {
            locale = Locale.getDefault();
        }
//        Log.d(TAG, locale.getLanguage());
        setSystemCurrentLocal(locale);
    }

    /**
     * 设置语言类型
     */
    public static void setApplicationLanguage(Context context) {
        String languageType = getLanguageType();
        Locale locale = getLanguageLocale(languageType);
        setApplicationLanguage(context, locale);
    }

    /**
     * 设置语言类型
     */
    public static void setApplicationLanguage(Context context, Locale locale) {
        Resources resources = context.getApplicationContext().getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        config.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LocaleList localeList = new LocaleList(locale);
            LocaleList.setDefault(localeList);
            config.setLocales(localeList);
            context.getApplicationContext().createConfigurationContext(config);
            Locale.setDefault(locale);
        }
        resources.updateConfiguration(config, dm);
    }


    public static void onConfigurationChanged(Context context) {
        saveSystemCurrentLanguage(context);
        setLocal(context);
        setApplicationLanguage(context);
    }

    public static Context setLocal(Context context) {
        String languageType = getLanguageType();
        Locale locale = getLanguageLocale(languageType);
        return updateResources(context, locale);
    }

    private static Context updateResources(Context context, Locale locale) {
        Locale.setDefault(locale);

        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        if (Build.VERSION.SDK_INT >= 17) {
            config.setLocale(locale);
            context = context.createConfigurationContext(config);
        } else {
            config.locale = locale;
            res.updateConfiguration(config, res.getDisplayMetrics());
        }
        return context;
    }

    public static Locale getSystemCurrentLocal() {
        return systemCurrentLocal;
    }

    public static void setSystemCurrentLocal(Locale local) {
        systemCurrentLocal = local;
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context createConfigurationResources(Context context, String languageType) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale;
        if (TextUtils.isEmpty(languageType)) {//如果没有指定语言使用系统首选语言
            locale = getSystemPreferredLanguage();
        } else {//指定了语言使用指定语言，没有则使用首选语言
            locale = getLanguageLocale(languageType);
        }
        configuration.setLocale(locale);
        return context.createConfigurationContext(configuration);
    }

    /**
     * 获取系统首选语言
     *
     * @return Locale
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Locale getSystemPreferredLanguage() {
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = LocaleList.getDefault().get(0);
        } else {
            locale = Locale.getDefault();
        }
        return locale;
    }

    /**
     * 获取语言国家
     *
     * @param languageType
     * @return
     */
    public static String getLanguageName(String languageType) {
        try {
            return LanguageConstant.languageNameMap.get(Integer.parseInt(languageType));
        } catch (Exception e) {
            e.printStackTrace();
            return LanguageConstant.languageNameMap.get(0);
        }
    }

    /**
     * 获取语言国家
     *
     * @param languageType
     * @return
     */
    public static String getLanguageName(int languageType) {
        try {
            return LanguageConstant.languageNameMap.get(languageType);
        } catch (Exception e) {
            e.printStackTrace();
            return LanguageConstant.languageNameMap.get(0);
        }
    }

    /**
     * 获取语言国国旗
     *
     * @param languageType
     * @return
     */
    public static int getLanguageLogo(String languageType) {
        try {
            return LanguageConstant.languageLogoMap.get(Integer.parseInt(languageType));
        } catch (Exception e) {
            e.printStackTrace();
            return LanguageConstant.languageLogoMap.get(0);
        }
    }

    /**
     * 获取语言国国旗
     *
     * @param languageType
     * @return
     */
    public static Locale getLanguageLocale(String languageType) {
        try {
            return LanguageConstant.languageLocaleMap.get(Integer.parseInt(languageType));
        } catch (Exception e) {
            e.printStackTrace();
            return LanguageConstant.languageLocaleMap.get(0);
        }
    }

    /**
     * 获取语言类型
     *
     * @param languageType
     * @return
     */
    public static int getLanguageType(String languageType) {
        try {
            return Integer.parseInt(languageType);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return LanguageConstant.LanguageType.SIMPLIFIED_CHINESE;
        }
    }

    /**
     * 是否需要翻译(双方语音是否一致)
     *
     * @param srcLanguageType 消息语言类型
     * @return
     */
    public static boolean needTranslate(String srcLanguageType) {
        if (StringUtil.isEmpty(srcLanguageType)) {
            return false;
        }
        String languageType = getLanguageType();
        if (!srcLanguageType.equals(languageType)) {
            return true;
        }
        return false;
    }
}
