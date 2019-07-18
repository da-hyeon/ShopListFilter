package com.hdh.shoplistfilter.ui.joininghelper.daum;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hdh.shoplistfilter.Constans;
import com.hdh.shoplistfilter.R;
import com.hdh.shoplistfilter.databinding.ActivityDaumWebViewBinding;

public class DaumWebViewActivity extends AppCompatActivity {

    private ActivityDaumWebViewBinding mBinding;

    class MyJavaScriptInterface
    {
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void processDATA(String data) {

            Bundle extra = new Bundle();
            Intent intent = new Intent();
            extra.putString(Constans.SEARCH_ADDRESS_DATA, data);
            intent.putExtras(extra);
            setResult(RESULT_OK, intent);
            finish();

        }
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_daum_web_view);
        mBinding.setDaumActivity(this);

        mBinding.wvDaum.getSettings().setJavaScriptEnabled(true);
        mBinding.wvDaum.addJavascriptInterface(new MyJavaScriptInterface(), "Android");

        mBinding.wvDaum.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                mBinding.wvDaum.loadUrl("javascript:sample2_execDaumPostcode();");
            }
        });

        mBinding.wvDaum.loadUrl("http://kjg123kg.cafe24.com/daum.html");
    }

}