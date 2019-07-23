package com.hdh.shoplistfilter.ui.search;

import android.widget.ListView;

import com.hdh.shoplistfilter.ui.base.BaseActivityContract;

public interface ShopSearchContract {
    interface View extends BaseActivityContract.View {
        void changeLayout(boolean status);
    }
    interface Presenter{
        void getJson();

        void setListView(ListView listView);
        void onTextChanged(String s);

        void clickDismiss();
    }
}