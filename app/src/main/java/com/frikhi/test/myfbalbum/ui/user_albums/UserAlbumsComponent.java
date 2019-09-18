package com.frikhi.test.myfbalbum.ui.user_albums;

import dagger.Component;
import com.frikhi.test.myfbalbum.di.ActivityScope;
import com.frikhi.test.myfbalbum.di.component.AppComponent;




@ActivityScope
@Component(dependencies = AppComponent.class, modules = UserAlbumsModule.class)

public interface UserAlbumsComponent {

    void inject(UserAlbumsActivity activity);

}

