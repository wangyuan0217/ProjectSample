package com.trump.library_common.http.okhttp;

import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;


/**
 * @author Trump
 * @time 2019/10/14 14:56
 * @desc
 */
public class HttpsTrustManager implements X509TrustManager {
    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) {
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) {
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}

