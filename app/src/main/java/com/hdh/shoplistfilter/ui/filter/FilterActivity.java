package com.hdh.shoplistfilter.ui.filter;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import com.hdh.shoplistfilter.R;
import com.hdh.shoplistfilter.databinding.ActivityFilterBinding;
import com.hdh.shoplistfilter.ui.base.BaseActivity;

public class FilterActivity extends BaseActivity implements FilterContract.View {

    private ActivityFilterBinding mBinding;
    private FilterContract.Presenter mPresenter;

    private Button[] ageButtons;
    private Button[] styleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_filter);
        mBinding.setFilterActivity(this);
        initData();

        //연령대 버튼 클릭
        for (int i = 0; i < ageButtons.length; i++) {
            int finalI = i;
            ageButtons[i].setOnClickListener(v ->
                    mPresenter.clickAge(finalI)
            );
        }

        //스타일 버튼 클릭
        for (int i = 0; i < styleButton.length; i++) {
            int finalI = i;
            styleButton[i].setOnClickListener(v ->
                    mPresenter.clickStyle(finalI)
            );
        }

        //닫기버튼 클릭
        mBinding.vDismiss.setOnClickListener(v->
                mPresenter.clickDismiss()
        );


        //완료버튼 클릭
        mBinding.btComplete.setOnClickListener(v->
                mPresenter.clickComplete()
        );

        //초기화 버튼 클릭
        mBinding.vReset.setOnClickListener(v->
                mPresenter.clickReset()
        );
    }

    /**
     * 데이터 초기화 및 생성
     */
    private void initData() {
        mPresenter = new FilterPresenter(this, this, this);


        ageButtons = new Button[]{
                mBinding.btAge10,
                mBinding.btAgeEarly20,
                mBinding.btAgeMid20,
                mBinding.btAgeLate20,
                mBinding.btAgeEarly30,
                mBinding.btAgeMid30,
                mBinding.btAgeLate30
        };

        styleButton = new Button[]{
                mBinding.btPeminin,
                mBinding.btModernChic,
                mBinding.btSimpleBasic,
                mBinding.btLovely,
                mBinding.btUnique,
                mBinding.btMissyStyle,
                mBinding.btCampusLook,
                mBinding.btVintage,
                mBinding.btSexyGlam,
                mBinding.btSchoolLook,
                mBinding.btRomantic,
                mBinding.btOfficeLook,
                mBinding.btLuxury,
                mBinding.btHollywoodStyle
        };

        //저장된 필터 불러오기
        mPresenter.loadFilterData();

    }

    /**
     * 연령대 버튼 상태 변경하기
     */
    @Override
    public void changeAgeState(int index) {
        //선택상태일떄
        if (ageButtons[index].getCurrentTextColor() == Color.WHITE){
            ageButtons[index].setBackground(getResources().getDrawable(R.drawable.round_button_mint_empty));
            ageButtons[index].setTextColor(getResources().getColor(R.color.filter_ageButtonOriginal));
        } else {
            ageButtons[index].setBackground(getResources().getDrawable(R.drawable.round_button_mint_full));
            ageButtons[index].setTextColor(Color.WHITE);
        }

    }

    /**
     * 스타일 버튼 상태 변경하기
     */
    @Override
    public void changeStyleState(int index) {
        //선택상태일떄
        if (styleButton[index].getCurrentTextColor() == Color.WHITE){
            styleButton[index].setBackground(getResources().getDrawable(R.drawable.round_button_red_empty));
            styleButton[index].setTextColor(getResources().getColor(R.color.filter_styleButtonOriginal));
        } else {
            styleButton[index].setBackground(getResources().getDrawable(R.drawable.round_button_red_full));
            styleButton[index].setTextColor(Color.WHITE);
        }
    }

    /**
     * 뒤로가기 클릭
     */
    @Override
    public void onBackPressed() {
        mPresenter.clickBackPressed();
    }
}
