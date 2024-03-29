package com.trump.library_common.http.okhttp;

import android.text.TextUtils;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.socks.library.KLog;
import com.trump.library_common.config.CommonConfig;
import com.trump.library_common.config.HttpConfigConstant;
import com.trump.library_common.app.BaseApplication;

import java.io.File;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

public enum OKHttpFactory {

    INSTANCE;

    private final OkHttpClient okHttpClient;

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    OKHttpFactory() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        //公共header
        builder.addInterceptor(buildHeaderInterceptor());

        //cache
        File fileCache = BaseApplication.getContext().getCacheDir();
        Cache cache = new Cache(fileCache, HttpConfigConstant.CACHE_SIZE);
        builder.cache(cache);

        //for debug log
        if (CommonConfig.LOG_ENABLE) {
            builder.interceptors().add(getHttpLoggingInterceptor());
        }

        builder.sslSocketFactory(getSSLSocketFactory(), new HttpsTrustManager());
        builder.hostnameVerifier((hostname, session) -> true);

        //add cookie
        SharedPrefsCookiePersistor persistor = new SharedPrefsCookiePersistor(BaseApplication.getContext());
        ClearableCookieJar mCookieJar = new PersistentCookieJar(new SetCookieCache(), persistor);
        builder.cookieJar(mCookieJar);

        //common config
        builder.readTimeout(HttpConfigConstant.READ_TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(HttpConfigConstant.WRITE_TIME_OUT, TimeUnit.SECONDS);
        builder.connectTimeout(HttpConfigConstant.CONNECT_TIME_OUT, TimeUnit.SECONDS);

        //need retry
        builder.retryOnConnectionFailure(true);

        okHttpClient = builder.build();
    }

    private static SSLSocketFactory getSSLSocketFactory() {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{new HttpsTrustManager()}, new SecureRandom());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sslContext.getSocketFactory();
    }

    private HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(message -> {
            if (TextUtils.isEmpty(message))
                return;
            String temp = message.substring(0, 1);
            if (message.startsWith("POST http://")
                    || message.startsWith("GET http")
                    || message.startsWith("--> GET http")
                    || message.startsWith("--> POST http")) {
                //请求url
                KLog.json(message);
            } else if (message.contains("=")
                    && !message.contains("Set-Cookie")
                    && !message.contains("Cache-Control")
                    && !message.contains("Content-Type")
                    && !message.contains("Content-Type")
                    && !message.contains("Keep-Alive")
                    && !message.contains("<--")) {
                //post时 请求参数
                KLog.json(message);
            } else if ("{".equals(temp) || "[".equals(temp)) {
                //请求结果
                KLog.json(message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    private Interceptor buildHeaderInterceptor() {
        return chain -> {
            Request originalRequest = chain.request();
//            String date = TimeUtil.getFormatTimeString(System.currentTimeMillis(), TimeUtil.Format.FORMAT_YYYYMMDDHHMMSSSSS);
//            String path = originalRequest.url().toString()
//                    .replace(BuildConfig.API_URL, "")
//                    .replace(BuildConfig.API_GAIWEN_BIGDATA, "")
//                    .replace(BuildConfig.API_GAIWEN, "");
//            if (path.contains("?")) {
//                path = path.substring(0, path.indexOf("?"));
//            }


            //app_id:iryjftomotjfnupl   app_secret:UEpEbTAxeExPc0ZqUWpURVk2NVZWdz09
            Request authorised = originalRequest.newBuilder()
                    .addHeader("app_id", "iryjftomotjfnupl")
                    .addHeader("app_secret", "UEpEbTAxeExPc0ZqUWpURVk2NVZWdz09")
                    .build();
            return chain.proceed(authorised);
        };
    }
}