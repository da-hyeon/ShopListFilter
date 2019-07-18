package com.hdh.shoplistfilter.ui.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.hdh.shoplistfilter.MyApplication;
import com.hdh.shoplistfilter.R;
import com.hdh.shoplistfilter.databinding.ActivityMainBinding;
import com.hdh.shoplistfilter.ui.base.BaseActivity;

public class MainActivity extends BaseActivity implements MainContract.View{

    private ActivityMainBinding mBinding;
    private MainContract.Presenter mPresenter;
    private MyApplication mMyApplication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setMainActivity(this);
        initData();

        mBinding.btFilter.setOnClickListener(v->
                mPresenter.clickFilter()
        );
    }

    /**
     * 데이터 초기화 및 생성
     */
    private void initData(){
        mPresenter = new MainPresenter(this , this);
        mMyApplication = MyApplication.getInstance();
        mMyApplication.setActivity(this);
    }

    /**
     * WeekText 변경하기
     */
    @Override
    public void changeWeekText(String week) {
        mBinding.tvRanking.setText(week);
    }

    @Override
    public void moveActivity(Intent intent) {
        startActivity(intent);
    }

    /**
     * 재진입 처리
     */
    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.loadFilterData();
        mPresenter.getJson();
        mPresenter.setListView(mBinding.lvShop);
    }
}
