package com.hdh.shoplistfilter.ui.web;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.hdh.shoplistfilter.Constans;
import com.hdh.shoplistfilter.ui.joininghelper.JoiningHelperActivity;

public class ShopWebViewPresenter implements ShopWebViewContract.Presenter {
    private ShopWebViewContract.View mView;
    private Context mContext;
    private Activity mActivity;

    public ShopWebViewPresenter(ShopWebViewContract.View mView, Context mContext, Activity mActivity) {
        this.mView = mView;
        this.mContext = mContext;
        this.mActivity = mActivity;
    }

    @Override
    public void getData(Intent intent) {
        String url = intent.getStringExtra(Constans.SHOP_URL);
        String shopName = intent.getStringExtra(Constans.SHOP_NAME);
        mView.showWebView(url);
        mView.changeShopName(shopName);
    }

    @Override
    public void clickDismiss() {
        mActivity.finish();
    }

    @Override
    public void clickJoiningHelper() {
        mContext.startActivity(new Intent(mContext , JoiningHelperActivity.class));
    }
}
