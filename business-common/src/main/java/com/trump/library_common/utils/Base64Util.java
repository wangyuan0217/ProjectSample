package com.trump.library_common.utils;

import android.util.Base64;

public class Base64Util {
    /**
     * 二进制数组 编码成 二进制数组
     *
     * @param input
     * @return
     */
    public static byte[] encodeToBytes(byte[] input) {
        return Base64.encode(input, Base64.NO_WRAP);
    }

    /**
     * 二进制数组 编码成 字符串
     *
     * @param input
     * @return
     */
    public static String encodeToString(byte[] input) {
        return Base64.encodeToString(input, Base64.NO_WRAP);
    }

    /**
     * 二进制数组 解码成 二进制数组
     *
     * @param input
     * @return
     */
    public static byte[] decode(byte[] input) {
        return Base64.decode(input, Base64.NO_WRAP);
    }

    /**
     * 字符串 解码成 二进制数组
     *
     * @return
     */
    public static byte[] decode(String str) {
        return Base64.decode(str, Base64.NO_WRAP);
    }
}
