package com.hdh.shoplistfilter.ui.joininghelper;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.hdh.shoplistfilter.Constans;
import com.hdh.shoplistfilter.R;
import com.hdh.shoplistfilter.ui.joininghelper.daum.DaumWebViewActivity;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static android.app.Activity.RESULT_OK;

public class JoiningHelperPresenter implements JoiningHelperContract.Presenter {

    private JoiningHelperContract.View mView;
    private Context mContext;
    private Activity mActivity;
    private int mYear, mMonth, mDay;


    public JoiningHelperPresenter(JoiningHelperContract.View mView, Context mContext, Activity mActivity) {
        this.mView = mView;
        this.mContext = mContext;
        this.mActivity = mActivity;
    }

    /**
     * 국번 설정 및 스피너 지정
     */
    @Override
    public void setAreaCodeSpinner(Spinner spinner) {
        String[] areaCode = mContext.getResources().getStringArray(R.array.AreaCodeSpinnerArray);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(mContext, R.layout.spinner_item, areaCode);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

    }

    /**
     * 핸드폰 앞자리 설정 및 스피너 지정
     */
    @Override
    public void setPhoneCodeSpinner(Spinner spinner) {
        String[] phoneCode = mContext.getResources().getStringArray(R.array.PhoneCodeSpinnerArray);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(mContext, R.layout.spinner_item, phoneCode);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    /**
     * 지역 설정 및 스피너 지정
     */
    @Override
    public void setAreaSpinner(Spinner spinner) {
        String[] area = mContext.getResources().getStringArray(R.array.AreaSpinnerArray);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(mContext, R.layout.spinner_item, area);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    /**
     * PasswordCheck 질문 설정 및 스피너 지정
     */
    @Override
    public void setPasswordCheckSpinner(Spinner spinner) {
        String[] passwordCheck = mContext.getResources().getStringArray(R.array.PasswordCheckSpinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(mContext, R.layout.spinner_item, passwordCheck);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    /**
     * 닫기 버튼 이벤트 처리
     */
    @Override
    public void clickDismiss() {
        mView.showWarningDialog("주의", "변경된 내용은 저장되지 않습니다.");
    }

    /**
     * 주소검색 버튼 클릭 이벤트 처리
     */
    @Override
    public void clickAddress() {
        Intent intent = new Intent(mContext, DaumWebViewActivity.class);
        mView.moveActivity(intent);
    }

    /**
     * 뒤로가기 클릭 이벤트 처리
     */
    @Override
    public void clickBackPressed() {
        clickDismiss();
    }

    /**
     * 선택된 주소 삭제 버튼 이벤트 처리
     */
    @Override
    public void clickAddressSelectedDismiss() {
        mView.hideAddressSelectedLayout();
        mView.showAddressFindLayout();
    }

    /**
     * 날짜 선택 클릭 이벤트 처리
     */
    @Override
    public void clickBirthday() {
        //사용자가 날짜를 선택하지 않았다면 현재날짜로 안내
        if (mYear == 0) {
            Calendar cal = new GregorianCalendar();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            new DatePickerDialog(mContext, mDateSetListener, year, month, day).show();
        } else {
            new DatePickerDialog(mContext, mDateSetListener, mYear, mMonth, mDay).show();
        }

    }

    /**
     * 생년월일 삭제 버튼 클릭 이벤트 처리
     */
    @Override
    public void clickBirthDismiss() {
        mView.changeBirthday("생년월일을 선택해주세요.");
        mView.hideBirthDismiss();
        mYear = 0;
    }

    /**
     * 완료버튼 클릭 이벤트 처리
     */
    @Override
    public void clickComplete(String userID, String userName, String userFirstHomeNumber, String userMidHomeNumber, String userLastHomeNumber, String userFirstPhoneNumber, String userMidPhoneNumber, String userLastPhoneNumber, String userFirstEmail, String userLastEmail, String userBirthday, String userArea, String userPasswordConfirmationQuestion, String userPasswordConfirmationAnswer) {

    }

    /**
     * 주소 선택 처리
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == Constans.SEARCH_ADDRESS_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                String data = intent.getExtras().getString(Constans.SEARCH_ADDRESS_DATA);
                if (data != null) {
                    mView.hideAddressFindLayout();
                    mView.showAddressSelectedLayout();
                    String[] address = data.split(",");

                    mView.changeAddressNumber(address[0]);
                    mView.changeAddress(address[1].substring(1));
                }
            }
        }
    }

    //날짜 대화상자 리스너 부분
    DatePickerDialog.OnDateSetListener mDateSetListener =

            (view, year, monthOfYear, dayOfMonth) -> {
                mYear = year;
                mMonth = monthOfYear;
                mDay = dayOfMonth;

                mView.changeBirthday(mYear + "년 " + (mMonth + 1) + "월 " + mDay + "일");
                mView.showBirthDismiss();
                //텍스트뷰의 값을 업데이트함
            };
}