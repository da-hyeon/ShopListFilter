package com.hdh.shoplistfilter.ui.web;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hdh.shoplistfilter.MyApplication;
import com.hdh.shoplistfilter.R;
import com.hdh.shoplistfilter.databinding.ActivityShopWebViewBinding;
import com.hdh.shoplistfilter.ui.base.BaseActivity;

public class ShopWebViewActivity extends BaseActivity implements ShopWebViewContract.View {

    private ShopWebViewContract.Presenter mPresenter;
    private ActivityShopWebViewBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_shop_web_view);
        mBinding.setShopWebViewActivity(this);
        initData();

        mBinding.vDismiss.setOnClickListener(v ->
                mPresenter.clickDismiss()
        );

        mBinding.vJoiningHelper.setOnClickListener(v ->
                mPresenter.clickJoiningHelper()
        );
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initData() {

        mBinding.wvShop.setWebViewClient(new WebViewClient() {

            @Override
            public void onLoadResource(WebView view, String url) {
                // 웹 페이지 리소스들을 로딩하면서 계속해서 호출된다.
                super.onLoadResource(view, url);

            }


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // 페이지 요청이 시작될 경우 호출된다.
                super.onPageStarted(view, url, favicon);
                Log.d("enterence", "start");
            }

            // 페이지 로딩이 종료시 호출된다.
            @Override
            public void onPageFinished(WebView view, String url) {
                if (url.equals("https://m.imvely.com/member/join.html")) {
                    view.loadUrl(mPresenter.enteredImvely());
                } else if (url.equals("https://www.liphop.co.kr/m/join_contract.html")) {
                    view.loadUrl(mPresenter.enteredLiphop());
                } else if (url.equals("http://m.naning9.com/member/join.php")) {
                    view.loadUrl(mPresenter.enteredNaning9());
                } else if (url.equals("http://m.marishe.com/join.mari")) {
                    view.loadUrl(mPresenter.enteredMarishe());
                } else if (url.equals("https://m.member.dscount.com/member/RegisterFormJob.asp")) {
                    view.loadUrl(mPresenter.enteredDahong());
                } else if (url.equals("https://m.gosister.co.kr/member/m_memberW2.asp")) {
                    view.loadUrl(mPresenter.enteredGosister());
                }
            }
        });

        //웹뷰세팅
        WebSettings mWebSettings = mBinding.wvShop.getSettings(); //세부 세팅 등록
        mWebSettings.setJavaScriptEnabled(true); // 웹페이지 자바스크립트 허용 여부
        mWebSettings.setSupportMultipleWindows(true); // 새창 띄우기 허용 여부
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true); // 자바스크립트 새창 띄우기(멀티뷰) 허용 여부
        mWebSettings.setLoadWithOverviewMode(true); // 메타태그 허용 여부
        mWebSettings.setUseWideViewPort(true); // 화면 사이즈 맞추기 허용 여부
        mWebSettings.setSupportZoom(false); // 화면 줌 허용 여부
        mWebSettings.setBuiltInZoomControls(true); // 화면 확대 축소 허용 여부
        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); // 컨텐츠 사이즈 맞추기
        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); // 브라우저 캐시 허용 여부
        mWebSettings.setDomStorageEnabled(true); // 로컬저장소 허용 여부

        mPresenter = new ShopWebViewPresenter(this, this, this);
        mPresenter.getData(getIntent());


    }

    /**
     * WebView 보여주기
     */
    @Override
    public void showWebView(String url) {
        mBinding.wvShop.loadUrl(url); // 접속 URL
    }

    /**
     * 쇼핑몰 이름 변경하기
     *
     * @param shopName
     */
    @Override
    public void changeShopName(String shopName) {
        mBinding.tvShopName.setText(shopName);
    }

    /**
     * 뒤로가기 클릭 시 웹에서 뒤로가기 적용
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mBinding.wvShop.canGoBack()) {
                mBinding.wvShop.goBack();
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
