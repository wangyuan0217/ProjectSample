package com.trump.library_common.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;

import java.lang.reflect.Field;

/**
 * 签名校验， 意义不大
 */
public class SignatureCheckUtil {

    public static final String trueSignMD5 = "xxxxx";

    /**
     * 使用较新的 API 检测
     */
    @SuppressLint("PackageManagerGetSignatures")
    public static boolean useNewAPICheck(Context context) {
        String nowSignMD5 = "";
        Signature[] signs = null;
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(
                        context.getPackageName(),
                        PackageManager.GET_SIGNING_CERTIFICATES);
                signs = packageInfo.signingInfo.getApkContentsSigners();
            } else {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(
                        context.getPackageName(), PackageManager.GET_SIGNATURES);
                signs = packageInfo.signatures;
            }
            // 得到签名MD5
            String signBase64 = Base64Util.encodeToString(signs[0].toByteArray());
            nowSignMD5 = MD5Util.MD5(signBase64);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return trueSignMD5.equals(nowSignMD5);
    }

    /**
     * 做普通的签名校验
     */
    public static boolean doNormalSignCheck(Context context) {
        String nowSignMD5 = "";
        try {
            // 得到签名的MD5
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            Signature[] signs = packageInfo.signatures;
            String signBase64 = Base64Util.encodeToString(signs[0].toByteArray());
            nowSignMD5 = MD5Util.MD5(signBase64);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return trueSignMD5.equals(nowSignMD5);
    }

    /**
     * 检测 PM 代理
     */
    @SuppressLint("PrivateApi")
    public static boolean checkPMProxy(Context context) {
        String truePMName = "android.content.pm.IPackageManager$Stub$Proxy";
        String nowPMName = "";
        try {
            // 被代理的对象是 PackageManager.mPM
            PackageManager packageManager = context.getPackageManager();
            Field mPMField = packageManager.getClass().getDeclaredField("mPM");
            mPMField.setAccessible(true);
            Object mPM = mPMField.get(packageManager);
            // 取得类名
            assert mPM != null;
            nowPMName = mPM.getClass().getName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 类名改变说明被代理了
        return truePMName.equals(nowPMName);
    }

    /**
     * 校验 application
     */
    public static boolean checkApplicationName(Activity context) {
        Application nowApplication = context.getApplication();
        String trueApplicationName = "MyAppXXX";
        String nowApplicationName = nowApplication.getClass().getSimpleName();
        return trueApplicationName.equals(nowApplicationName);
    }
}
