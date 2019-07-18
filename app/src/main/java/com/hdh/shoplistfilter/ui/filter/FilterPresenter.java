package com.hdh.shoplistfilter.ui.filter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.hdh.shoplistfilter.Constans;

import java.util.Arrays;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class FilterPresenter implements FilterContract.Presenter {
    private FilterContract.View mView;
    private Context mContext;
    private Activity mActivity;

    private int[] ageStatus = new int[7];
    private int[] styleStatus = new int[14];


    FilterPresenter(FilterContract.View mView, Context mContext, Activity mActivity) {
        this.mView = mView;
        this.mContext = mContext;
        this.mActivity = mActivity;
    }

    /**
     * 저장되어있는 필터 정보를 가져옴.
     */
    @Override
    public void loadFilterData() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(Constans.FILTER_SAVE, MODE_PRIVATE);
        String[] ageStatus = Objects.requireNonNull(sharedPreferences.getString(Constans.FILTER_AGE_SAVE, ""))
                .replace(" ", "")
                .replace("[", "")
                .replace("]", "")
                .split(",");
        if (ageStatus.length > 3){
            for (int i = 0; i < ageStatus.length; i++) {
                if (ageStatus[i].equals("1")) {
                    mView.changeAgeState(i);
                }
                this.ageStatus[i] = Integer.parseInt(ageStatus[i]);
            }
        }

        String[] styleStatus = Objects.requireNonNull(sharedPreferences.getString(Constans.FILTER_STYLE_SAVE, ""))
                .replace(" " , "")
                .replace("[", "")
                .replace("]", "")
                .split(",");
        if (styleStatus.length > 3){
            for (int i = 0; i < styleStatus.length; i++) {
                if (styleStatus[i].equals("1")) {
                    mView.changeStyleState(i);
                }
                this.styleStatus[i] = Integer.parseInt(styleStatus[i]);
            }
        }
    }

    /**
     * 연령대 버튼 클릭 이벤트 처리
     */
    @Override
    public void clickAge(int index) {
        if (ageStatus[index] == 0)
            ageStatus[index] = 1;
        else
            ageStatus[index] = 0;

        mView.changeAgeState(index);
    }

    /**
     * 스타일 버튼 클릭 이벤트 처리
     */
    @Override
    public void clickStyle(int index) {
        if (styleStatus[index] == 0)
            styleStatus[index] = 1;
        else
            styleStatus[index] = 0;

        mView.changeStyleState(index);
    }

    /**
     * 완료 버튼 클릭 이벤트 처리
     */
    @Override
    public void clickComplete() {

        SharedPreferences ageFilter = mContext.getSharedPreferences(Constans.FILTER_SAVE, MODE_PRIVATE);

        SharedPreferences.Editor editor = ageFilter.edit();
        editor.putString(Constans.FILTER_AGE_SAVE, Arrays.toString(ageStatus));
        editor.putString(Constans.FILTER_STYLE_SAVE, Arrays.toString(styleStatus));
        Log.d("Arrays", Arrays.toString(ageStatus));
        Log.d("Arrays", Arrays.toString(styleStatus));
        editor.apply();

        mActivity.finish();
    }

    /**
     * 닫기 버튼 클릭 이벤트 처리
     */
    @Override
    public void clickDismiss() {
        mView.showWarningDialog("주의", "변경된 내용이 저장되지 않습니다.");
    }

    /**
     * 리셋 버튼 클릭 이벤트 처리
     */
    @Override
    public void clickReset() {

        for(int i = 0 ; i < ageStatus.length; i++){
            if (ageStatus[i] == 0)
                continue;

            mView.changeAgeState(i);
            ageStatus[i] = 0;
        }

        for (int i = 0; i < styleStatus.length; i++){
            if (styleStatus[i] == 0)
                continue;

            mView.changeStyleState(i);
            styleStatus[i] = 0;
        }
    }

    /**
     * 뒤로가기 클릭 이벤트 처리
     */
    @Override
    public void clickBackPressed() {
        clickDismiss();
    }
}
