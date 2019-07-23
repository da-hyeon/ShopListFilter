package com.hdh.shoplistfilter.ui.search;

import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.hdh.shoplistfilter.ui.base.BaseActivityContract;

public interface ShopSearchContract {
    interface View extends BaseActivityContract.View {
        void changeLayout(boolean status);
    }
    interface Presenter{
        void getJson();

        void setListView(RecyclerView recyclerView);

        void onTextChanged(String s);

        void clickDismiss();
    }
}