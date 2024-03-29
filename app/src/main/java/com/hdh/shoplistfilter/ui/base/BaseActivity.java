package com.hdh.shoplistfilter.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kinda.alert.KAlertDialog;

public class BaseActivity extends AppCompatActivity implements BaseActivityContract.View{

    private BaseActivityContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new BaseActivityPresenter(this ,this , this);
    }

    /**
     * 경고 다이얼로그 보여주기
     */
    @Override
    public void showWarningDialog(String title, String content) {
        new KAlertDialog(this, KAlertDialog.WARNING_TYPE)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmText("확인")
                .setConfirmClickListener(sDialog -> {
                    sDialog.dismissWithAnimation();
                    finish();
                })
                .setCancelText("취소")
                .setCancelClickListener(KAlertDialog::dismissWithAnimation)
                .show();
    }

    /**
     * 성공 다이얼로그 보이기
     */
    @Override
    public void showSuccessDialog(String title, String content) {
        new KAlertDialog(this, KAlertDialog.SUCCESS_TYPE)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmText("확인")
                .setConfirmClickListener(sDialog -> {
                    sDialog.dismissWithAnimation();
                    finish();
                })
                .show();
    }

    /**
     * 액티비티 종료
     */
    @Override
    public void removeActivity() {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
