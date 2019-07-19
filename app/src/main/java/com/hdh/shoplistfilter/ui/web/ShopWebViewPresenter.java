package com.hdh.shoplistfilter.ui.web;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.hdh.shoplistfilter.Constans;
import com.hdh.shoplistfilter.MyApplication;
import com.hdh.shoplistfilter.data.model.UserInfo;
import com.hdh.shoplistfilter.ui.joininghelper.JoiningHelperActivity;

public class ShopWebViewPresenter implements ShopWebViewContract.Presenter {
    private ShopWebViewContract.View mView;
    private Context mContext;
    private Activity mActivity;
    private UserInfo mUserInfo;

    ShopWebViewPresenter(ShopWebViewContract.View mView, Context mContext, Activity mActivity) {
        this.mView = mView;
        this.mContext = mContext;
        this.mActivity = mActivity;
        mUserInfo = MyApplication.getUserInfoInstance();
    }

    /**
     * 넘겨받은 쇼핑몰 URL , 이름 가져오기
     */
    @Override
    public void getData(Intent intent) {
        String url = intent.getStringExtra(Constans.SHOP_URL);
        String shopName = intent.getStringExtra(Constans.SHOP_NAME);
        mView.showWebView(url);
        mView.changeShopName(shopName);
    }

    /**
     * 닫기 버튼 클릭 이벤트 처리
     */
    @Override
    public void clickDismiss() {
        mActivity.finish();
    }

    /**
     * 가입 도우미 아이콘 클릭 이벤트 처리
     */
    @Override
    public void clickJoiningHelper() {
        mContext.startActivity(new Intent(mContext , JoiningHelperActivity.class));
    }

    /**
     * Imvely 사이트 접속 이벤트 처리
     */
    @Override
    public String enteredImvely() {
        String hint;
        if (mUserInfo.getUserPasswordConfirmationQuestion() < 10){
            hint = "hint_0" + mUserInfo.getUserPasswordConfirmationQuestion();
        } else {
            hint = "hint_" + mUserInfo.getUserPasswordConfirmationQuestion();
        }

        return "javascript:function afterLoad() {"
                + "document.getElementById('member_id').value = '" + mUserInfo.getUserID() + "';"
                + "document.getElementById('hint').value = '" + hint + "';"
                + "document.getElementById('hint_answer').value = '" + mUserInfo.getUserPasswordConfirmationAnswer() + "';"
                + "document.getElementById('postcode1').value = '" + mUserInfo.getUserAddressNumber() + "';"
                + "document.getElementById('addr1').value = '" + mUserInfo.getUserAddress() + "';"
                + "document.getElementById('addr2').value = '" + mUserInfo.getUserRestAddress() + "';"
                + "document.getElementById('mobile1').value = '" + mUserInfo.getUserFirstPhoneNumber() + "';"
                + "document.getElementById('mobile2').value = '" + mUserInfo.getUserMidPhoneNumber() + "';"
                + "document.getElementById('mobile3').value = '" + mUserInfo.getUserLastPhoneNumber() + "';"
                + "document.getElementById('email1').value = '" + mUserInfo.getUserFirstEmail() + "';"
                + "document.getElementById('email2').value = '" + mUserInfo.getUserLastEmail() + "';"
                + "document.getElementById('email3').value = '" + mUserInfo.getUserLastEmail() + "';"
                + "document.getElementById('birth_year').value = '" + mUserInfo.getUserBirthdayYear() + "';"
                + "document.getElementById('birth_month').value = '" + mUserInfo.getUserBirthdayMonth() + "';"
                + "document.getElementById('birth_day').value = '" + mUserInfo.getUserBirthdayDay() + "';"
                + "};"
                + "afterLoad();";
    }

    /**
     * Liphop 사이트 접속 이벤트 처리
     */
    @Override
    public String enteredLiphop() {
        String month , day;
        if (Integer.parseInt(mUserInfo.getUserBirthdayMonth()) < 10){
            month = "0"+mUserInfo.getUserBirthdayMonth();
        } else {
            month = mUserInfo.getUserBirthdayMonth();
        }

        if (Integer.parseInt(mUserInfo.getUserBirthdayDay()) < 10){
            day = "0"+mUserInfo.getUserBirthdayDay();
        } else {
            day = mUserInfo.getUserBirthdayDay();
        }

        return "javascript:function afterLoad() {"
                + "document.getElementById('hname').value = '" + mUserInfo.getUserName() + "';"
                + "document.getElementById('id').value = '" + mUserInfo.getUserID() + "';"
                + "document.getElementById('email').value = '" + mUserInfo.getUserFirstEmail() + "@" + mUserInfo.getUserLastEmail() +"';"
                + "document.getElementsByName('birthyear')[0].value = '" + mUserInfo.getUserBirthdayYear() + "';"
                + "document.getElementsByName('birthmonth')[0].value = '" + month + "';"
                + "document.getElementsByName('birthdate')[0].value = '" + day + "';"
                + "document.getElementById('etcphone').value = '" + mUserInfo.getUserFirstPhoneNumber() + mUserInfo.getUserMidPhoneNumber() + mUserInfo.getUserLastPhoneNumber() + "';"
                + "};"
                + "afterLoad();";
    }

    /**
     * Naning9 사이트 접속 이벤트 처리
     */
    @Override
    public String enteredNaning9() {
        String month , day;
        if (Integer.parseInt(mUserInfo.getUserBirthdayMonth()) < 10){
            month = "0"+mUserInfo.getUserBirthdayMonth();
        } else {
            month = mUserInfo.getUserBirthdayMonth();
        }

        if (Integer.parseInt(mUserInfo.getUserBirthdayDay()) < 10){
            day = "0"+mUserInfo.getUserBirthdayDay();
        } else {
            day = mUserInfo.getUserBirthdayDay();
        }

        return "javascript:function afterLoad() {"
                + "document.getElementById('name').value = '" + mUserInfo.getUserName() + "';"
                + "document.getElementById('id').value = '" + mUserInfo.getUserID() + "';"
                + "document.getElementById('zipcode').value = '" + mUserInfo.getUserAddressNumber() + "';"
                + "document.getElementById('addr1').value = '" + mUserInfo.getUserAddress() + "';"
                + "document.getElementById('addr2').value = '" + mUserInfo.getUserRestAddress() + "';"
                + "document.getElementById('email').value = '" + mUserInfo.getUserFirstEmail() + "@" + mUserInfo.getUserLastEmail() +"';"
                + "document.getElementById('byear').value = '" + mUserInfo.getUserBirthdayYear() + "';"
                + "document.getElementsByName('bmonth')[0].value = '" + month + "';"
                + "document.getElementsByName('bday')[0].value = '" + day + "';"
                + "document.getElementById('cp1').value = '" + mUserInfo.getUserFirstPhoneNumber() + "';"
                + "document.getElementById('cp2').value = '" + mUserInfo.getUserMidPhoneNumber() + "';"
                + "document.getElementById('cp3').value = '" + mUserInfo.getUserLastPhoneNumber() + "';"
                + "};"
                + "afterLoad();";
    }

    /**
     * Marishe 사이트 접속 이벤트 처리
     */
    @Override
    public String enteredMarishe() {
        String month , day;
        if (Integer.parseInt(mUserInfo.getUserBirthdayMonth()) < 10){
            month = "0"+mUserInfo.getUserBirthdayMonth();
        } else {
            month = mUserInfo.getUserBirthdayMonth();
        }

        if (Integer.parseInt(mUserInfo.getUserBirthdayDay()) < 10){
            day = "0"+mUserInfo.getUserBirthdayDay();
        } else {
            day = mUserInfo.getUserBirthdayDay();
        }

        return "javascript:function afterLoad() {"
                + "document.getElementsByName('useremail')[0].value = '" + mUserInfo.getUserFirstEmail() + "@" + mUserInfo.getUserLastEmail() +"';"
                + "document.getElementsByName('username')[0].value = '" + mUserInfo.getUserName() + "';"
                + "document.getElementsByName('user_birth1')[0].value = '" + mUserInfo.getUserBirthdayYear() + "';"
                + "document.getElementsByName('user_birth2')[0].value = '" + month + "';"
                + "document.getElementsByName('user_birth3')[0].value = '" + day + "';"
                + "document.getElementsByName('user_tel')[0].value = '" + mUserInfo.getUserFirstPhoneNumber() + mUserInfo.getUserMidPhoneNumber() + mUserInfo.getUserLastPhoneNumber() + "';"
                + "};"
                + "afterLoad();";
    }

    /**
     * Dahong 사이트 접속 이벤트 처리
     */
    @Override
    public String enteredDahong() {
        String month , day;
        if (Integer.parseInt(mUserInfo.getUserBirthdayMonth()) < 10){
            month = "0"+mUserInfo.getUserBirthdayMonth();
        } else {
            month = mUserInfo.getUserBirthdayMonth();
        }

        if (Integer.parseInt(mUserInfo.getUserBirthdayDay()) < 10){
            day = "0"+mUserInfo.getUserBirthdayDay();
        } else {
            day = mUserInfo.getUserBirthdayDay();
        }

        return "javascript:function afterLoad() {"
                + "document.getElementById('member_name').value = '" + mUserInfo.getUserName() + "';"
                + "document.getElementById('member_id').value = '" + mUserInfo.getUserID() + "';"
                + "document.getElementById('birth_year').value = '" + mUserInfo.getUserBirthdayYear() + "';"
                + "document.getElementById('birth_month').value = '" + month + "';"
                + "document.getElementById('birth_day').value = '" + day + "';"
                + "document.getElementById('member_zipcode').value = '" + mUserInfo.getUserAddressNumber() + "';"
                + "document.getElementById('member_addr1').value = '" + mUserInfo.getUserAddress() + "';"
                + "document.getElementById('member_addr2').value = '" + mUserInfo.getUserRestAddress() + "';"
                + "document.getElementById('member_email1').value = '" + mUserInfo.getUserFirstEmail() + "';"
                + "document.getElementById('member_email2').value = '" + mUserInfo.getUserLastEmail() + "';"
                + "document.getElementById('member_email3').value = '" + mUserInfo.getUserLastEmail() + "';"
                + "document.getElementById('home_ddd').value = '" + mUserInfo.getUserFirstHomeNumber() + "';"
                + "document.getElementById('home_phone1').value = '" + mUserInfo.getUserMidHomeNumber() + "';"
                + "document.getElementById('home_phone2').value = '" + mUserInfo.getUserLastHomeNumber() + "';"
                + "document.getElementById('cp_phone1').value = '" + mUserInfo.getUserFirstPhoneNumber() + "';"
                + "document.getElementById('cp_phone2').value = '" + mUserInfo.getUserMidPhoneNumber() + "';"
                + "document.getElementById('cp_phone3').value = '" + mUserInfo.getUserLastPhoneNumber() + "';"

                + "};"
                + "afterLoad();";
    }

    /**
     * Gosister 사이트 접속 이벤트 처리
     */
    @Override
    public String enteredGosister() {

        return "javascript:function afterLoad() {"
                + "document.getElementsByName('userid')[0].value = '" + mUserInfo.getUserID() + "';"
                + "document.getElementsByName('name')[0].value = '" + mUserInfo.getUserName() + "';"
                + "document.getElementsByName('HB_Y')[0].value = '" + mUserInfo.getUserBirthdayYear() + "';"
                + "document.getElementsByName('HB_M')[0].value = '" + mUserInfo.getUserBirthdayMonth() + "';"
                + "document.getElementsByName('HB_D')[0].value = '" + mUserInfo.getUserBirthdayDay() + "';"
                + "document.getElementsByName('tel1_1')[0].value = '" + mUserInfo.getUserFirstPhoneNumber() + "';"
                + "document.getElementsByName('tel1_2')[0].value = '" + mUserInfo.getUserMidPhoneNumber() + "';"
                + "document.getElementsByName('tel1_3')[0].value = '" + mUserInfo.getUserLastPhoneNumber() + "';"
                + "document.getElementsByName('email')[0].value = '" + mUserInfo.getUserFirstEmail() + "@" + mUserInfo.getUserLastEmail() +"';"

                + "};"
                + "afterLoad();";
    }
}
