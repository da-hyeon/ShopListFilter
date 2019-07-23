package com.hdh.shoplistfilter.ui.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.hdh.shoplistfilter.MyApplication;
import com.hdh.shoplistfilter.R;
import com.hdh.shoplistfilter.databinding.ActivityMainBinding;
import com.hdh.shoplistfilter.ui.base.BaseActivity;

public class MainActivity extends BaseActivity implements MainContract.View{

    private ActivityMainBinding mBinding;
    private MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setMainActivity(this);
        initData();

        mBinding.btFilter.setOnClickListener(v->
                mPresenter.clickFilter()
        );

        mBinding.btSearch.setOnClickListener(v->
                mPresenter.clickSearch()
        );
    }

    /**
     * 데이터 초기화 및 생성
     */
    private void initData(){
        mPresenter = new MainPresenter(this , this);
        MyApplication.getInstance().setActivity(this);
    }

    /**
     * WeekText 변경하기
     */
    @Override
    public void changeWeekText(String week) {
        mBinding.tvRanking.setText(week);
    }

    /**
     * 액티비티 이동
     */
    @Override
    public void moveActivity(Intent intent) {
        startActivity(intent);
    }

    /**
     * Toast 메시지 출력
     */
    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Filter 버튼 상태 변경하기
     */
    @Override
    public void changeFilterButtonStatus(boolean status) {
        if (status){
            mBinding.btFilter.setBackground(getResources().getDrawable(R.drawable.round_button_mint_full));
            mBinding.btFilter.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_filter_white_24dp, 0);
            mBinding.btFilter.setTextColor(Color.WHITE);
        } else {
            //설정마저하기
            mBinding.btFilter.setBackground(getResources().getDrawable(R.drawable.round_button));
            mBinding.btFilter.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_filter_black_24dp, 0);
            mBinding.btFilter.setTextColor(getResources().getColor(R.color.filter_text));
        }
    }

    /**
     * 재진입 처리
     */
    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.loadFilterData();
        mPresenter.getJson();
        mPresenter.setListView(mBinding.rvShop);
    }

    /**
     * 뒤로가기
     */
    @Override
    public void onBackPressed() {
        mPresenter.onBackPressed();
    }
}
