package com.frikhi.test.myfbalbum.ui.album_pictures;

import dagger.Component;
import com.frikhi.test.myfbalbum.di.ActivityScope;
import com.frikhi.test.myfbalbum.di.component.AppComponent;





@ActivityScope
@Component(dependencies = AppComponent.class, modules = AlbumPicturesModule.class)
public interface AlbumPicturesComponent {

    void inject(AlbumPicturesActivity activity);

}

