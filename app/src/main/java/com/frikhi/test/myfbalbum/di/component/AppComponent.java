package com.frikhi.test.myfbalbum.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import com.frikhi.test.myfbalbum.di.module.AppModule;



@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    Application application();
}
