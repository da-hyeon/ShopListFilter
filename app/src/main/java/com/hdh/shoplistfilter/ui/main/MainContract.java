package com.hdh.shoplistfilter.ui.main;

import android.content.Intent;
import android.widget.ListView;

import com.hdh.shoplistfilter.ui.base.BaseActivityContract;

public interface MainContract {
    interface View extends BaseActivityContract.View {
        void moveActivity(Intent intent);

        void showToast(String message);

        void changeWeekText(String week);
        void changeFilterButtonStatus(boolean status);

    }
    interface Presenter{
        void loadFilterData();
        void onBackPressed();

        void getJson();

        void setListView(ListView listView);

        void clickFilter();
        void clickSearch();
    }
}
