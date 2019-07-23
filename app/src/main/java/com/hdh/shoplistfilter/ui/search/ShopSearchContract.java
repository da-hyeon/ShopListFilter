package com.hdh.shoplistfilter.ui.search;

import android.widget.ListView;

import com.hdh.shoplistfilter.ui.main.MainContract;

public interface ShopSearchContract {
    interface View extends MainContract.View {
        void changeLayout(boolean status);
    }
    interface Presenter{
        void getJson();

        void setListView(ListView listView);
        void onTextChanged(String s);

        void clickDismiss();
    }
}