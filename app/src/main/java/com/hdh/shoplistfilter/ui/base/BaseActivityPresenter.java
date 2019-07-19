package com.hdh.shoplistfilter.ui.base;

import android.app.Activity;
import android.content.Context;

public class BaseActivityPresenter implements BaseActivityContract.Presenter {

    private BaseActivityContract.View mView;
    private Context mContext;
    private Activity mActivity;


    BaseActivityPresenter(BaseActivityContract.View mView, Context mContext, Activity mActivity) {
        this.mView = mView;
        this.mContext = mContext;
        this.mActivity = mActivity;
    }
}
