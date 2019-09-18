package com.frikhi.test.myfbalbum.ui.login;

import dagger.Component;
import com.frikhi.test.myfbalbum.di.ActivityScope;
import com.frikhi.test.myfbalbum.di.component.AppComponent;




@ActivityScope
@Component(dependencies = AppComponent.class, modules = LoginModule.class)
public interface LoginComponent {

    void inject(LoginActivity activity);

}

