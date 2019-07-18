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
        String hint = "";
        if (mUserInfo.getUserPasswordConfirmationQuestion() < 10){
            hint = "hint_0" + mUserInfo.getUserPasswordConfirmationQuestion();
        } else {
            hint = "hint_" + mUserInfo.getUserPasswordConfirmationQuestion();
        }

        String script = "javascript:function afterLoad() {"
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
                + "document.getElementById('birth_year').value = '" + mUserInfo.getUserBirthdayYear() + "';"
                + "document.getElementById('birth_month').value = '" + mUserInfo.getUserBirthdayMonth() + "';"
                + "document.getElementById('birth_day').value = '" + mUserInfo.getUserBirthdayDay() + "';"
                + "};"
                + "afterLoad();";

        return script;
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

        String script = "javascript:function afterLoad() {"
                + "document.getElementById('hname').value = '" + mUserInfo.getUserName() + "';"
                + "document.getElementById('id').value = '" + mUserInfo.getUserID() + "';"
                + "document.getElementById('email').value = '" + mUserInfo.getUserFirstEmail() + "@" + mUserInfo.getUserLastEmail() +"';"
                + "document.getElementById('birthyear').value = '" + mUserInfo.getUserBirthdayYear() + "';"
                + "document.getElementById('birthmonth').value = '" + month + "';"
                + "document.getElementById('birthdate').value = '" + day + "';"
                + "document.getElementById('etcphone').value = '" + mUserInfo.getUserFirstPhoneNumber() + mUserInfo.getUserMidPhoneNumber() + mUserInfo.getUserLastPhoneNumber() + "';"
                + "};"
                + "afterLoad();";

        return script;
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

        String script = "javascript:function afterLoad() {"
                + "document.getElementById('name').value = '" + mUserInfo.getUserName() + "';"
                + "document.getElementById('id').value = '" + mUserInfo.getUserID() + "';"
                + "document.getElementById('zipcode').value = '" + mUserInfo.getUserAddressNumber() + "';"
                + "document.getElementById('addr1').value = '" + mUserInfo.getUserAddress() + "';"
                + "document.getElementById('addr2').value = '" + mUserInfo.getUserRestAddress() + "';"
                + "document.getElementById('email').value = '" + mUserInfo.getUserFirstEmail() + "@" + mUserInfo.getUserLastEmail() +"';"
                + "document.getElementById('byear').value = '" + mUserInfo.getUserBirthdayYear() + "';"
                + "document.getElementById('bmonth').value = '" + month + "';"
                + "document.getElementById('bday').value = '" + day + "';"
                + "document.getElementById('cp1').value = '" + mUserInfo.getUserFirstPhoneNumber() + "';"
                + "document.getElementById('cp2').value = '" + mUserInfo.getUserMidPhoneNumber() + "';"
                + "document.getElementById('cp3').value = '" + mUserInfo.getUserLastPhoneNumber() + "';"
                + "};"
                + "afterLoad();";

        return script;
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

        String script = "javascript:function afterLoad() {"
                + "document.getElementById('useremail').value = '" + mUserInfo.getUserFirstEmail() + "@" + mUserInfo.getUserLastEmail() +"';"
                + "document.getElementById('username').value = '" + mUserInfo.getUserName() + "';"
                + "document.getElementById('user_birth1').value = '" + mUserInfo.getUserBirthdayYear() + "';"
                + "document.getElementById('user_birth2').value = '" + month + "';"
                + "document.getElementById('user_birth3').value = '" + day + "';"
                + "};"
                + "afterLoad();";

        return script;
    }

    /**
     * Dahong 사이트 접속 이벤트 처리
     */
    @Override
    public String enteredDahong() {
        return null;
    }

    /**
     * Gosister 사이트 접속 이벤트 처리
     */
    @Override
    public String enteredGosister() {
        return null;
    }
}
