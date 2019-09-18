package com.frikhi.test.myfbalbum.ui.login;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;



public class LoginContract {

    interface View {
        void loginSuccess(String userId);
        void loginFailed();
    }

    interface Presenter {
        void loginClickCallBack(LoginButton loginButton, CallbackManager callbackManager);
    }
}
