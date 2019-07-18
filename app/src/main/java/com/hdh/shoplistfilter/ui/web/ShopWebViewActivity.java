package com.hdh.shoplistfilter.ui.web;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hdh.shoplistfilter.R;
import com.hdh.shoplistfilter.databinding.ActivityShopWebViewBinding;
import com.hdh.shoplistfilter.ui.base.BaseActivity;

public class ShopWebViewActivity extends BaseActivity implements ShopWebViewContract.View {

    private ShopWebViewContract.Presenter mPresenter;
    private ActivityShopWebViewBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this , R.layout.activity_shop_web_view);
        mBinding.setShopWebViewActivity(this);
        initData();

        mBinding.vDismiss.setOnClickListener(v->
            mPresenter.clickDismiss()
        );

        mBinding.vJoiningHelper.setOnClickListener(v->
            mPresenter.clickJoiningHelper()
        );
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initData(){
        mBinding.wvShop.setWebViewClient(new WebViewClient()); // 클릭시 새창 안뜨게
        //웹뷰세팅
        WebSettings mWebSettings = mBinding.wvShop.getSettings(); //세부 세팅 등록
        mWebSettings.setJavaScriptEnabled(true); // 웹페이지 자바스크립트 허용 여부
        mWebSettings.setSupportMultipleWindows(false); // 새창 띄우기 허용 여부
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(false); // 자바스크립트 새창 띄우기(멀티뷰) 허용 여부
        mWebSettings.setLoadWithOverviewMode(true); // 메타태그 허용 여부
        mWebSettings.setUseWideViewPort(true); // 화면 사이즈 맞추기 허용 여부
        mWebSettings.setSupportZoom(false); // 화면 줌 허용 여부
        mWebSettings.setBuiltInZoomControls(false); // 화면 확대 축소 허용 여부
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
