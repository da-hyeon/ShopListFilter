package com.hdh.shoplistfilter.ui.joininghelper;

import android.content.Intent;
import android.widget.Spinner;

import com.hdh.shoplistfilter.ui.base.BaseActivityContract;

public interface JoiningHelperContract {
    interface View extends BaseActivityContract.View {
        void moveActivity(Intent intent);

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
                           String userFirstHomeNumber,
                           String userMidHomeNumber,
                           String userLastHomeNumber,
                           String userFirstPhoneNumber,
                           String userMidPhoneNumber,
                           String userLastPhoneNumber,
                           String userFirstEmail,
                           String userLastEmail,
                           String userBirthday,
                           String userArea,
                           String userPasswordConfirmationQuestion,
                           String userPasswordConfirmationAnswer);

        void onActivityResult(int requestCode, int resultCode, Intent intent);
    }
}
