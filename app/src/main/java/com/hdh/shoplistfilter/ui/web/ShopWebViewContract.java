package com.hdh.shoplistfilter.ui.web;

import android.content.Intent;

import com.hdh.shoplistfilter.ui.base.BaseActivityContract;

public interface ShopWebViewContract {
    interface View extends BaseActivityContract.View {
        void showWebView(String url);

        void changeShopName(String shopName);
    }
    interface Presenter{
        void getData(Intent intent);
        void clickDismiss();
        void clickJoiningHelper();

        String enteredImvely();
        String enteredLiphop();
        String enteredNaning9();
        String enteredMarishe();
        String enteredDahong();
        String enteredGosister();
    }
}
