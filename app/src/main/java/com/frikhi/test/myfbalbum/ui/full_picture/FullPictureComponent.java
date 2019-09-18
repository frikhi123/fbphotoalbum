package com.frikhi.test.myfbalbum.ui.full_picture;

import dagger.Component;
import com.frikhi.test.myfbalbum.di.ActivityScope;
import com.frikhi.test.myfbalbum.di.component.AppComponent;




@ActivityScope
@Component(dependencies = AppComponent.class, modules = FullPictureModule.class)
public interface FullPictureComponent {

    void inject(FullPictureActivity activity);

}

