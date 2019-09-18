package com.frikhi.test.myfbalbum.ui.album_pictures;

import dagger.Module;
import dagger.Provides;
import com.frikhi.test.myfbalbum.di.ActivityScope;



@Module
public class AlbumPicturesModule {

    private AlbumPicturesContract.View mView;


    public AlbumPicturesModule(AlbumPicturesContract.View mView) {
        this.mView = mView;
    }

    @ActivityScope
    @Provides
    public AlbumPicturesContract.View provideContext(){
        return mView;
    }

}


