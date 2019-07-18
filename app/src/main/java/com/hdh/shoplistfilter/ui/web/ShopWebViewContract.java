package com.hdh.shoplistfilter.ui.web;

import android.content.Intent;

public interface ShopWebViewContract {
    interface View{
        void showWebView(String url);

        void changeShopName(String shopName);
    }
    interface Presenter{
        void getData(Intent intent);
        void clickDismiss();
        void clickJoiningHelper();
    }
}
