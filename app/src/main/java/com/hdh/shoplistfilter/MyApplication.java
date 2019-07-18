package com.hdh.shoplistfilter;

import android.app.Activity;
import android.app.Application;

import com.hdh.shoplistfilter.data.model.ShopList;
import com.hdh.shoplistfilter.data.model.UserInfo;

public class MyApplication extends Application {

    private static MyApplication appInstance;

    private Activity mActivity;
    private static ShopList mShopList;
    private static UserInfo mUserInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
    }

    /**
     * MyApplication Singleton
     */
    public static MyApplication getInstance() {
        if (appInstance == null) {
            appInstance = new MyApplication();
        }
        return appInstance;
    }

    /**
     * ShopList Singleton
     */
    public static ShopList getShopListInstance() {
        if (mShopList == null) {
            mShopList = new ShopList();
        }
        return mShopList;
    }


    /**
     * ShopList Singleton
     */
    public static UserInfo getUserInfoInstance() {
        if (mUserInfo == null) {
            mUserInfo = new UserInfo();
        }
        return mUserInfo;
    }

    /**
     * getActivity
     */
    public Activity getActivity() {
        return mActivity;
    }

    /**
     * setActivity
     */
    public void setActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }
}
