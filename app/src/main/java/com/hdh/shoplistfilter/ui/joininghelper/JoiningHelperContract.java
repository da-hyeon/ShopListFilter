package com.hdh.shoplistfilter.ui.joininghelper;

import android.content.Intent;
import android.widget.Spinner;

import com.hdh.shoplistfilter.ui.base.BaseActivityContract;

public interface JoiningHelperContract {
    interface View extends BaseActivityContract.View {
        void moveActivity(Intent intent);

        void setFormInitial(String userID,
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
                            String userPasswordConfirmationAnswer);

        void showAddressFindLayout();
        void showAddressSelectedLayout();
        void showBirthDismiss();

        void hideAddressFindLayout();
        void hideAddressSelectedLayout();
        void hideBirthDismiss();

        void changeAddressNumber(String addressNumber);
        void changeAddress(String address);
        void changeBirthday(String birthday);
    }

    interface Presenter extends BaseActivityContract.Presenter {
        void setFormInitial();
        void setAreaCodeSpinner(Spinner spinner);
        void setPhoneCodeSpinner(Spinner spinner);
        void setAreaSpinner(Spinner spinner);
        void setPasswordCheckSpinner(Spinner spinner);

        void clickDismiss();
        void clickAddress();
        void clickBackPressed();
        void clickAddressSelectedDismiss();
        void clickBirthday();
        void clickBirthDismiss();
        void clickComplete(String userID,
                           String userName,
                           String userRestAddress,
                           String userFirstHomeNumber, String userMidHomeNumber, String userLastHomeNumber,
                           String userFirstPhoneNumber, String userMidPhoneNumber, String userLastPhoneNumber,
                           String userFirstEmail, String userLastEmail,
                           String userBirthday,
                           String userArea,
                           int userPasswordConfirmationQuestion, String userPasswordConfirmationAnswer ,
                           int userFirstHomePosition,
                           int userFirstPhoneNumberPosition,
                           int userAreaPosition);

        void onActivityResult(int requestCode, int resultCode, Intent intent);
    }
}
