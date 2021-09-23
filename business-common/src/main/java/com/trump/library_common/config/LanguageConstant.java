package com.trump.library_common.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Description:语言常量类
 *
 * @Author: yaozhiheng
 * @Date: 2019/1/2
 */
public class LanguageConstant {

    /**
     * 当前用户语言类型ID
     */
    public static class LanguageType {
        /**
         * 中文简体
         */
        public static final int SIMPLIFIED_CHINESE = TranslateConstant.type_中文简体;
        /**
         * 繁體中文
         */
        public static final int TRADITIONAL_CHINESE = TranslateConstant.type_中文繁体;
        /**
         * 英语
         */
        public static final int ENGLISH = TranslateConstant.type_英语;
        /**
         * 韩语
         */
        public static final int KOREAN = TranslateConstant.type_韩语;
        /**
         * 法语
         */
        public static final int FRANCE = TranslateConstant.type_法语;
        /**
         * 意大利语
         */
        public static final int ITALY = TranslateConstant.type_意大利语;
        /**
         * 日语
         */
        public static final int JAPAN = TranslateConstant.type_日语;
        /**
         * 俄语
         */
        public static final int RUSSIAN = TranslateConstant.type_俄语;
        /**
         * 西班牙语
         */
        public static final int SPANISH = TranslateConstant.type_西班牙语;
        /**
         * 德语
         */
        public static final int GERMAN = TranslateConstant.type_德语;
        /**
         * 葡萄牙语
         */
        public static final int PORTUGUESE = TranslateConstant.type_葡萄牙语;
        /**
         * 粤语
         */
        public static final int CANTONESE = TranslateConstant.type_粤语;
        /**
         * 阿拉伯语
         */
        public static final int ARABIC = TranslateConstant.type_阿拉伯语;
        /**
         * 印尼语
         */
        public static final int INDONESIAN = TranslateConstant.type_印尼语;

    }

    /**
     * 支持国际化的14种语言
     */
    public static List<Integer> languageList = new ArrayList();

    static {
        languageList.add(LanguageType.SIMPLIFIED_CHINESE);
//        languageList.add(LanguageType.TRADITIONAL_CHINESE);
        languageList.add(LanguageType.ENGLISH);
//        languageList.add(LanguageType.KOREAN);
//        languageList.add(LanguageType.FRANCE);
//        languageList.add(LanguageType.ITALY);
//        languageList.add(LanguageType.JAPAN);
        languageList.add(LanguageType.RUSSIAN);
//        languageList.add(LanguageType.SPANISH);
//        languageList.add(LanguageType.GERMAN);
//        languageList.add(LanguageType.PORTUGUESE);
//        languageList.add(LanguageType.CANTONESE);
//        languageList.add(LanguageType.ARABIC);
//        languageList.add(LanguageType.INDONESIAN);
    }


    /**
     * 支持国际化的14种语言
     */
    public static Map<Integer, Locale> languageLocaleMap = new HashMap<Integer, Locale>(14) {{
        put(LanguageType.ENGLISH, Locale.ENGLISH);//英语
        put(LanguageType.SIMPLIFIED_CHINESE, Locale.SIMPLIFIED_CHINESE);//简体中文
        put(LanguageType.TRADITIONAL_CHINESE, Locale.TRADITIONAL_CHINESE);//繁体中文
        put(LanguageType.FRANCE, Locale.FRANCE);//法语
        put(LanguageType.GERMAN, Locale.GERMANY);//德语
        put(LanguageType.ITALY, Locale.ITALY);//意大利语
        put(LanguageType.JAPAN, Locale.JAPAN);//日语
        put(LanguageType.KOREAN, Locale.KOREAN);//韩语
        put(LanguageType.RUSSIAN, new Locale("ru"));//俄语
        put(LanguageType.SPANISH, new Locale("es"));//西班牙语
        put(LanguageType.PORTUGUESE, new Locale("pt"));//葡萄牙语
        put(LanguageType.CANTONESE, Locale.SIMPLIFIED_CHINESE);//粤语
        put(LanguageType.ARABIC, new Locale("ar"));//阿拉伯语
        put(LanguageType.INDONESIAN, new Locale("in"));//印尼语
    }};

    /**
     * 支持国际化的14种语言名称
     */
    public static Map<Integer, String> languageNameMap = new HashMap<Integer, String>(14) {{
        put(LanguageType.ENGLISH, "English");//英语
        put(LanguageType.SIMPLIFIED_CHINESE, "language_CHS");//简体中文
        put(LanguageType.TRADITIONAL_CHINESE, "language_TW");//繁体中文
//        put(LanguageType.FRANCE, BaseApplication.getContext().getResources().getString(R.string.language_FRA));//法语
//        put(LanguageType.GERMAN, BaseApplication.getContext().getResources().getString(R.string.language_DEU));//德语
//        put(LanguageType.ITALY, BaseApplication.getContext().getResources().getString(R.string.language_ITA));//意大利语
//        put(LanguageType.JAPAN, BaseApplication.getContext().getResources().getString(R.string.language_JPN));//日语
//        put(LanguageType.KOREAN, BaseApplication.getContext().getResources().getString(R.string.language_KOR));//韩语
//        put(LanguageType.RUSSIAN, BaseApplication.getContext().getResources().getString(R.string.language_RUS));//俄语
//        put(LanguageType.SPANISH, BaseApplication.getContext().getResources().getString(R.string.language_SPA));//西班牙语
//        put(LanguageType.PORTUGUESE, BaseApplication.getContext().getResources().getString(R.string.language_POR));//葡萄牙语
//        put(LanguageType.CANTONESE, BaseApplication.getContext().getResources().getString(R.string.language_HK));//粤语
//        put(LanguageType.ARABIC, BaseApplication.getContext().getResources().getString(R.string.language_ARA));//阿拉伯语
//        put(LanguageType.INDONESIAN, BaseApplication.getContext().getResources().getString(R.string.language_ID));//印尼语
    }};

    /**
     * 支持国际化的14种语言国旗
     */
    public static Map<Integer, Integer> languageLogoMap = new HashMap<Integer, Integer>(14) {{
        put(LanguageType.ENGLISH, 0);//英语s
        put(LanguageType.SIMPLIFIED_CHINESE, 0);//简体中文
        put(LanguageType.TRADITIONAL_CHINESE, 0);//繁体中文
        
//        put(LanguageType.FRANCE, R.mipmap.guoqi_faguo);//法语
//        put(LanguageType.GERMAN, R.mipmap.guoqi_deguo);//德语
//        put(LanguageType.ITALY, R.mipmap.guoqi_yidali);//意大利语
//        put(LanguageType.JAPAN, R.mipmap.guoqi_riben);//日语
//        put(LanguageType.KOREAN, R.mipmap.guoqi_hanguo);//韩语
//        put(LanguageType.RUSSIAN, R.mipmap.guoqi_eluosi);//俄语
//        put(LanguageType.SPANISH, R.mipmap.guoqi_xibanya);//西班牙语
//        put(LanguageType.PORTUGUESE, R.mipmap.guoqi_putaoya);//葡萄牙语
//        put(LanguageType.CANTONESE, R.mipmap.guoqi_zhongguo);//粤语
//        put(LanguageType.ARABIC, R.mipmap.guoqi_alabo);//阿拉伯语
//        put(LanguageType.INDONESIAN, R.mipmap.guoqi_ydnxy);//印尼语
    }};


}
