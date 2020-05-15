package com.trump.main.test.activity;

import android.os.Bundle;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.trump.library_common.ui.base.activity.BaseActivity;
import com.trump.main.R;
import com.trump.main.R2;
import com.trump.main.X5WebView;
import com.trump.main.test.contract.TestContract;
import com.trump.main.test.presenter.TestPresenter;

import butterknife.BindView;

@Route(path = "/main/test")
public class TestActivity extends BaseActivity<TestContract.View, TestContract.Presenter> implements TestContract.View {

    @BindView(R2.id.webView)
    X5WebView mWebView;

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_test;
    }

    @Override
    protected TestContract.Presenter initPresenter() {
        return new TestPresenter();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        //WebViewClient：在影响【View】的事件到来时，会通过WebViewClient中的方法回调通知用户
        //WebChromeClient：当影响【浏览器】的事件到来时，就会通过WebChromeClient中的方法回调通知用法。


        //mWebView.addJavascriptInterface(new JsInterface(this), "js_method");

        //mWebView.setDownloadListener(new MyWebViewDownLoadListener(mContext));

//        mWebView.setWebViewClient(new com.tencent.smtt.sdk.WebViewClient() {
//
//            @Override
//            public void onPageStarted(com.tencent.smtt.sdk.WebView webView, String s, Bitmap bitmap) {
//                super.onPageStarted(webView, s, bitmap);
//            }
//
//            @Override
//            public boolean shouldOverrideUrlLoading(com.tencent.smtt.sdk.WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//
//            @Override
//            public void onReceivedSslError(com.tencent.smtt.sdk.WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
//                sslErrorHandler.proceed();
//            }
//        });

//        mWebView.setWebChromeClient(new WebChromeClient() {

//            //修复资讯部分广告页面某些按钮电视不响应的bug
//            @Override
//            public boolean onCreateWindow(com.tencent.smtt.sdk.WebView webView, boolean isDialog, boolean isUserGesture, Message resultMsg) {
//                X5WebView x5WebView = new X5WebView(HomeInfoDetailAdActivity.this);
//                X5WebView.WebViewTransport transport = (com.tencent.smtt.sdk.WebView.WebViewTransport) resultMsg.obj;
//                //x5WebView.setWebChromeClient(new CustomWebChromeClient(activity));
//                x5WebView.setWebViewClient(new WebViewClient() {
//                    @Override
//                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                        Utils.loadUrl(mContext, mWebView, url, getIntent());
//                        return true;
//                    }
//
//                });
//                transport.setWebView(x5WebView);
//                resultMsg.sendToTarget();
//                return true;
//            }
//        });
    }

    @Override
    protected void initData() {
        mWebView.loadUrl("https://www.amazon.cn/?tag=xxs05-23");
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            mWebView.clearHistory();
            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.loadUrl("about:blank");
            mWebView.stopLoading();
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }
}
