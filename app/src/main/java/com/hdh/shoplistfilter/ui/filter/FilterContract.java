package com.hdh.shoplistfilter.ui.filter;

import com.hdh.shoplistfilter.ui.base.BaseActivityContract;

public interface FilterContract {
    interface View extends BaseActivityContract.View {
        void changeAgeState(int index);
        void changeStyleState(int index);
    }
    interface Presenter extends BaseActivityContract.Presenter{
        void loadFilterData();

        void clickAge(int index);
        void clickStyle(int index);
        void clickComplete();
        void clickDismiss();
        void clickReset();

        void clickBackPressed();
    }
}
