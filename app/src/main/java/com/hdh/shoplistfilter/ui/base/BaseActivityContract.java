package com.hdh.shoplistfilter.ui.base;

public interface BaseActivityContract {
    interface View{
        void showWarningDialog(String title, String content);
        void showSuccessDialog(String title, String content);
    }
    interface Presenter{

    }
}
