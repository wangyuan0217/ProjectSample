package com.trump.library_common.utils;

import android.net.Uri;
import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class StringUtil {
    public static String formatPrice(String price) {
        DecimalFormat format = new DecimalFormat("0.00");
        return format.format(new BigDecimal(price));
    }

    /**
     * 将List<String> 转成List<Uri>
     *
     * @param strList
     * @return
     */
    public static List<Uri> getListUriFromListString(List<String> strList) {
        List<Uri> uriList = null;
        try {
            uriList = new ArrayList<>();
            for (int i = 0; i < strList.size(); i++) {
                Uri uri = Uri.parse(strList.get(i));
                uriList.add(uri);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uriList;
    }

    /**
     * 将以逗号分隔的字符串转换成字符串List
     *
     * @param valStr
     * @return String[]
     */
    public static List<String> strToList(String valStr) {
        List<String> list = new ArrayList<>();
        try {
            if (valStr != null) {
                valStr = valStr + ",";
                while (valStr.indexOf(',') > 0) {
                    list.add(valStr.substring(0, valStr.indexOf(',')));
                    valStr = valStr.substring(valStr.indexOf(',') + 1, valStr.length());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 将以逗号分隔的字符串转换成字符串数组
     *
     * @param valStr
     * @return String[]
     */
    public static String[] strList(String valStr) {
        int i = 0;
        String TempStr = valStr;
        String[] returnStr = new String[valStr.length() + 1
                - TempStr.replace(",", "").length()];
        valStr = valStr + ",";
        while (valStr.indexOf(',') > 0) {
            returnStr[i] = valStr.substring(0, valStr.indexOf(','));
            valStr = valStr.substring(valStr.indexOf(',') + 1, valStr.length());

            i++;
        }
        return returnStr;
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(Object str) {
        return str == null || "".equals(str.toString().trim());
    }

    /**
     * 判断后缀是否是指定值
     *
     * @param text
     * @param suffix
     * @return
     */
    public static boolean isSuffixStr(String text, String suffix) {
        try {
            String mySuffix = text.substring(text.lastIndexOf(".") + 1);
            return mySuffix.equals(suffix);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 过滤字符串中包含超链接
     *
     * @param str
     * @return
     */
    public static String filerLinkStr(Object str) {
        String formatStr = "";
        if (str != null) {
            formatStr = str.toString().replaceAll("HYPERLINK", "");
            formatStr = formatStr.replaceAll("", "");
            int startIndex = formatStr.indexOf("\"http");
            int lastIndex = formatStr.lastIndexOf("http");
            if (startIndex == -1 || lastIndex == -1) {
                return formatStr;
            }
            String subStr = formatStr.substring(startIndex, lastIndex).trim();
            formatStr = formatStr.replace(subStr, "").trim();
            subStr = subStr.replaceAll("\"", "");
            formatStr = formatStr.replace(subStr, "").trim();
            formatStr = formatStr.replace(subStr, "").trim();
            formatStr = formatStr.replace("   ", "").trim();
        }
        return formatStr;

    }

    /**
     * 拼凑URL字符串
     *
     * @param url1 第一个URL
     * @param url2 第二个URL
     * @return
     */
    public static String combineUrl(String url1, String url2) {
        String sep = "/";
        boolean hasEnd = url1.endsWith(sep);
        boolean hasStart = url2.startsWith(sep);
        if (hasEnd && hasStart) {
            return url1.concat(url2.substring(1));
        } else if (!hasEnd && !hasStart) {
            return url1.concat(sep).concat(url2);
        } else {
            return url1.concat(url2);
        }
    }

    /**
     * List转换为字符串并加入分隔符
     *
     * @param list      字符串数组
     * @param separator 分隔符
     * @return
     */
    public static String listToString(List<String> list, CharSequence separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                sb.append(list.get(i));
            } else {
                sb.append(list.get(i));
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    /**
     * 转码
     *
     * @param source
     * @return
     */
    public static String urlDecodeUTF8(String source) {
        String result = source;
        try {
            result = java.net.URLDecoder.decode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 去除字符串中的空格
     *
     * @return
     */
    public static String delBlank(String content) {
        if (content == null) {
            return "";
        }
        int index = 0;//清除当前空格
        StringBuilder sBuilder = new StringBuilder(content);
        while (index < sBuilder.length()) {
            if (sBuilder.charAt(index) == ' ') {
                sBuilder.deleteCharAt(index);
            } else {
                index++;
            }
        }
        return sBuilder.toString();
    }


    /**
     * 检测String是否全是中文
     *
     * @param name
     * @return
     */
    public static boolean checkNameChese(String name) {
        boolean res = false;
        char[] cTemp = name.toCharArray();
        for (int i = 0; i < name.length(); i++) {
            if (isChinese(cTemp[i])) {
                res = true;
                break;
            }
        }
        return res;
    }

    /**
     * 判定输入汉字
     *
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }


    /**
     * 判断是否包含特殊字符
     *
     * @param str
     * @return
     */
    public static boolean hasSpecialCharacter(String str) {
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * 判断支付传是否不为空
     *
     * @param url 字符串内容
     * @return true 不为空  false 为空
     */
    public static boolean isNullStr(String url) {
        if (url == null || url.length() <= 0 || url.equals("null")) {
            return false;
        }
        return true;
    }
    /**
     *
     * @param url 字符串内容
     * @return true 不为空  false 为空
     */
    /**
     * 判断支付传是否不为空
     *
     * @param url 字符串内容
     * @param str true 不为空  false 为空
     * @return
     */
    public static boolean isNullStr(String url, String str) {
        if (isNullStr(url)) {
            if (url.equals(str)) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * 正则表达式 判断手机号
     *
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static boolean isChinaPhoneLegal(String str)
            throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(166)|(17[0-8])|(18[0-9])|(19[8-9])|(147,145))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    //判断是否是手机号
    public static boolean isMobile(String mobiles) {
        /**
         * 判断字符串是否符合手机号码格式
         * 移动号段: 134,135,136,137,138,139,147,150,151,152,157,158,159,170,178,182,183,184,187,188
         * 联通号段: 130,131,132,145,155,156,170,171,175,176,185,186
         * 电信号段: 133,149,153,170,173,177,180,181,189
         * @param str
         * @return 待检测的字符串
         */
        //去除空格
        String str1 = mobiles.replaceAll(" ", "");
        //去除 -
        String str2 = str1.replaceAll("-", "");

//        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        String regExp = "^((13[0-9])|(15[^4])|(166)|(17[0-8])|(18[0-9])|(19[8-9])|(14[5,7,9]))\\d{8}$";
        if (TextUtils.isEmpty(str2)) {
            return false;
        } else {
            return str2.matches(regExp);
        }
    }

    /**
     * 手机号用****号隐藏中间数字
     * 123****1234
     *
     * @param phone
     * @return
     */
    public static String settingphone(String phone) {
        String phone_s = phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        return phone_s;
    }

    /**
     * 隐藏身份证中间数字用*表示
     *
     * @param idCard
     * @return
     */
    public static String ReplaceWithStarAndEnd(String idCard) {
        if (isNullStr(idCard)) {
            return idCard.replaceAll("(\\d{3})\\d{11}(\\d{4})", "$1***********$2");
        }
        return "";
    }

    /**
     * 手机号 344 显示 中间空格隔开
     * 123 1234 1234
     *
     * @param phoneNumber
     * @return
     */
    public static String setblankphone(String phoneNumber) {
        if (isMobile(phoneNumber)) {
            int len = phoneNumber.length();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < len; i++) {
                builder.append(phoneNumber.charAt(i));
                if (i == 2 || i == 6) {
                    if (i != len - 1) {
                        builder.append(" ");
                    }
                }
            }
            return builder.toString();
        }
        return phoneNumber;
    }

}
