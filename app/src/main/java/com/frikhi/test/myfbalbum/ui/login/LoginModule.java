package com.frikhi.test.myfbalbum.ui.login;

import dagger.Module;
import dagger.Provides;
import com.frikhi.test.myfbalbum.di.ActivityScope;



@Module
public class LoginModule {

    private LoginContract.View mView;


    public LoginModule(LoginContract.View mView) {
        this.mView = mView;
    }

    @ActivityScope
    @Provides
    public LoginContract.View provideContext(){
        return mView;
    }

}


