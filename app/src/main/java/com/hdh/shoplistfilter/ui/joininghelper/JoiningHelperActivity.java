package com.hdh.shoplistfilter.ui.joininghelper;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.hdh.shoplistfilter.Constans;
import com.hdh.shoplistfilter.R;
import com.hdh.shoplistfilter.databinding.ActivityJoiningHelperBinding;
import com.hdh.shoplistfilter.ui.base.BaseActivity;

public class JoiningHelperActivity extends BaseActivity implements JoiningHelperContract.View {

    private JoiningHelperContract.Presenter mPresenter;
    private ActivityJoiningHelperBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_joining_helper);
        mBinding.setJoiningHelper(this);

        initData();

        mBinding.vDismiss.setOnClickListener(v ->
                mPresenter.clickDismiss()
        );

        mBinding.btAddress.setOnClickListener(v ->
                mPresenter.clickAddress()
        );

        mBinding.ivDismiss.setOnClickListener(v ->
                mPresenter.clickAddressSelectedDismiss()
        );

        mBinding.llBirthday.setOnClickListener(v ->
                mPresenter.clickBirthday()
        );

        mBinding.ivBirthDismiss.setOnClickListener(v ->
                mPresenter.clickBirthDismiss()
        );

        mBinding.btComplete.setOnClickListener(v ->
                mPresenter.clickComplete(
                        mBinding.etID.getText().toString(),
                        mBinding.etName.getText().toString(),
                        mBinding.sAreaCode.getSelectedItem().toString(),
                        mBinding.etHomeMidNumber.getText().toString(),
                        mBinding.etHomeLastNumber.getText().toString(),
                        mBinding.sPhoneCode.getSelectedItem().toString(),
                        mBinding.etPhoneMidNumber.getText().toString(),
                        mBinding.etPhoneLastNumber.getText().toString(),
                        mBinding.etEmailFirst.getText().toString(),
                        mBinding.etEmailLast.getText().toString(),
                        mBinding.tvBirthday.getText().toString(),
                        mBinding.sArea.getSelectedItem().toString(),
                        mBinding.sPasswordCheck.getSelectedItem().toString(),
                        mBinding.etPasswordAnswer.getText().toString()
                )
        );
    }

    private void initData() {
        mPresenter = new JoiningHelperPresenter(this, this, this);
        mPresenter.setAreaCodeSpinner(mBinding.sAreaCode);
        mPresenter.setPhoneCodeSpinner(mBinding.sPhoneCode);
        mPresenter.setAreaSpinner(mBinding.sArea);
        mPresenter.setPasswordCheckSpinner(mBinding.sPasswordCheck);
    }

    /**
     * 뒤로가기 클릭
     */
    @Override
    public void onBackPressed() {
        mPresenter.clickBackPressed();
    }

    @Override
    public void moveActivity(Intent intent) {
        startActivityForResult(intent, Constans.SEARCH_ADDRESS_ACTIVITY);
    }

    @Override
    public void showAddressFindLayout() {
        mBinding.llFindAddress.setVisibility(View.VISIBLE);
    }

    @Override
    public void showAddressSelectedLayout() {
        mBinding.llAddressSelected.setVisibility(View.VISIBLE);
    }

    @Override
    public void showBirthDismiss() {
        mBinding.llBirthDismiss.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideAddressFindLayout() {
        mBinding.llFindAddress.setVisibility(View.GONE);
    }

    @Override
    public void hideAddressSelectedLayout() {
        mBinding.llAddressSelected.setVisibility(View.GONE);
    }

    @Override
    public void hideBirthDismiss() {
        mBinding.llBirthDismiss.setVisibility(View.GONE);
    }

    @Override
    public void changeAddressNumber(String addressNumber) {
        mBinding.tvAddressNumber.setText(addressNumber);
    }

    @Override
    public void changeAddress(String address) {
        mBinding.tvAddress.setText(address);

    }

    @Override
    public void changeBirthday(String birthday) {
        mBinding.tvBirthday.setText(birthday);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        mPresenter.onActivityResult(requestCode, resultCode, intent);
    }
}
