package com.frikhi.test.myfbalbum;

import android.app.Application;

import com.frikhi.test.myfbalbum.di.component.AppComponent;
import com.frikhi.test.myfbalbum.di.component.DaggerAppComponent;
import com.frikhi.test.myfbalbum.di.module.AppModule;



public class App extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
