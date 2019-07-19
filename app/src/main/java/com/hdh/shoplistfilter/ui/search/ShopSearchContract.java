package com.hdh.shoplistfilter.ui.search;

import android.widget.ListView;

public interface ShopSearchContract {
    interface View{
        void changeLayout(boolean status);
    }
    interface Presenter{
        void getJson();

        void setListView(ListView listView);
        void onTextChanged(String s);

        void clickDismiss();
    }
}