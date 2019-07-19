package com.hdh.shoplistfilter.ui.search;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.hdh.shoplistfilter.R;
import com.hdh.shoplistfilter.databinding.ActivityShopSearchBinding;
import com.hdh.shoplistfilter.ui.base.BaseActivity;

public class ShopSearchActivity extends BaseActivity implements ShopSearchContract.View {

    private ShopSearchContract.Presenter mPresenter;
    private ActivityShopSearchBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this ,R.layout.activity_shop_search);
        mBinding.setShopSearchActivity(this);

        initData();

        mBinding.vDismiss.setOnClickListener(v->
            mPresenter.clickDismiss()
        );

        mBinding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPresenter.onTextChanged(s.toString());
                mPresenter.setListView(mBinding.lvShop);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 데이터 생성 및 초기화
     */
    private void initData(){
        mPresenter = new ShopSearchPresenter(this ,this, this);
        mPresenter.getJson();
    }

    /**
     * 레이아웃 변경하기
     */
    @Override
    public void changeLayout(boolean status) {
        if (status) {
            mBinding.llNotFoundShop.setVisibility(View.GONE);
            mBinding.lvShop.setVisibility(View.VISIBLE);
        } else {
            mBinding.llNotFoundShop.setVisibility(View.VISIBLE);
            mBinding.lvShop.setVisibility(View.GONE);
        }
    }
}
