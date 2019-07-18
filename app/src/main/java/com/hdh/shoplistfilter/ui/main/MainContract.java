package com.hdh.shoplistfilter.ui.main;

import android.content.Intent;
import android.widget.ListView;

public interface MainContract {
    interface View{
        void changeWeekText(String week);
        void moveActivity(Intent intent);
    }
    interface Presenter{
        void loadFilterData();

        void getJson();

        void setListView(ListView listView);

        void clickFilter();
    }
}
