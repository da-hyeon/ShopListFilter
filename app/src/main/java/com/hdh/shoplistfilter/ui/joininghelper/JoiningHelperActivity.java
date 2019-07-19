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

        //닫기 버튼 클릭
        mBinding.vDismiss.setOnClickListener(v ->
                mPresenter.clickDismiss()
        );

        //주소 버튼 클릭
        mBinding.btAddress.setOnClickListener(v ->
                mPresenter.clickAddress()
        );

        //주소삭제 버튼 클릭
        mBinding.ivDismiss.setOnClickListener(v ->
                mPresenter.clickAddressSelectedDismiss()
        );

        //생일입력 클릭
        mBinding.llBirthday.setOnClickListener(v ->
                mPresenter.clickBirthday()
        );

        //생일 삭제 클릭
        mBinding.ivBirthDismiss.setOnClickListener(v ->
                mPresenter.clickBirthDismiss()
        );

        //완료 버튼 클릭
        mBinding.btComplete.setOnClickListener(v ->
                mPresenter.clickComplete(
                        mBinding.etID.getText().toString(),
                        mBinding.etName.getText().toString(),
                        mBinding.etRestAddress.getText().toString(),
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
                        mBinding.sPasswordCheck.getSelectedItemPosition(),
                        mBinding.etPasswordAnswer.getText().toString() ,
                        mBinding.sAreaCode.getSelectedItemPosition(),
                        mBinding.sPhoneCode.getSelectedItemPosition(),
                        mBinding.sArea.getSelectedItemPosition()
                )
        );
    }

    /**
     * 데이터 초기화 및 생성
     */
    private void initData() {
        mPresenter = new JoiningHelperPresenter(this, this, this);
        mPresenter.setAreaCodeSpinner(mBinding.sAreaCode);
        mPresenter.setPhoneCodeSpinner(mBinding.sPhoneCode);
        mPresenter.setAreaSpinner(mBinding.sArea);
        mPresenter.setPasswordCheckSpinner(mBinding.sPasswordCheck);

        mPresenter.setFormInitial();
    }

    /**
     * 뒤로가기 클릭
     */
    @Override
    public void onBackPressed() {
        mPresenter.clickBackPressed();
    }

    /**
     * 액티비티 이동
     */
    @Override
    public void moveActivity(Intent intent) {
        startActivityForResult(intent, Constans.SEARCH_ADDRESS_ACTIVITY);
    }

    /**
     * 저장되어있는 유저 정보를 적음.
     */
    @Override
    public void setFormInitial(String userID,
                               String userName,
                               String userRestAddress,
                               int userFirstHomeNumber,
                               String userMidHomeNumber,
                               String userLastHomeNumber,
                               int userFirstPhoneNumber,
                               String userMidPhoneNumber,
                               String userLastPhoneNumber,
                               String userFirstEmail,
                               String userLastEmail,
                               int userArea,
                               int userPasswordConfirmationQuestion,
                               String userPasswordConfirmationAnswer) {

        mBinding.etID.setText(userID);

        mBinding.etName.setText(userName);

        mBinding.etRestAddress.setText(userRestAddress);

        mBinding.sAreaCode.setSelection(userFirstHomeNumber);
        mBinding.etHomeMidNumber.setText(userMidHomeNumber);
        mBinding.etHomeLastNumber.setText(userLastHomeNumber);

        mBinding.sPhoneCode.setSelection(userFirstPhoneNumber);
        mBinding.etPhoneMidNumber.setText(userMidPhoneNumber);
        mBinding.etPhoneLastNumber.setText(userLastPhoneNumber);

        mBinding.etEmailFirst.setText(userFirstEmail);
        mBinding.etEmailLast.setText(userLastEmail);

        mBinding.sArea.setSelection(userArea);
        mBinding.sPasswordCheck.setSelection(userPasswordConfirmationQuestion);
        mBinding.etPasswordAnswer.setText(userPasswordConfirmationAnswer);
    }

    /**
     * 주소찾기 레이아웃 보이기
     */
    @Override
    public void showAddressFindLayout() {
        mBinding.llFindAddress.setVisibility(View.VISIBLE);
    }

    /**
     * 선택된주소 레이아웃 보이기
     */
    @Override
    public void showAddressSelectedLayout() {
        mBinding.llAddressSelected.setVisibility(View.VISIBLE);
    }

    /**
     * 생일 레이아웃 보이기
     */
    @Override
    public void showBirthDismiss() {
        mBinding.llBirthDismiss.setVisibility(View.VISIBLE);
    }

    /**
     * 주소찾기 레이아웃 숨기기
     */
    @Override
    public void hideAddressFindLayout() {
        mBinding.llFindAddress.setVisibility(View.GONE);
    }

    /**
     * 선택된주소 레이아웃 숨기기
     */
    @Override
    public void hideAddressSelectedLayout() {
        mBinding.llAddressSelected.setVisibility(View.GONE);
    }

    /**
     * 생일 레이아웃 숨기기
     */
    @Override
    public void hideBirthDismiss() {
        mBinding.llBirthDismiss.setVisibility(View.GONE);
    }

    /**
     * 우편번호 Text 변경하기
     */
    @Override
    public void changeAddressNumber(String addressNumber) {
        mBinding.tvAddressNumber.setText(addressNumber);
    }

    /**
     * 메인주소 변경하기
     */
    @Override
    public void changeAddress(String address) {
        mBinding.tvAddress.setText(address);

    }

    /**
     * 생일 Text 변경하기
     */
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
