package com.frikhi.test.myfbalbum.ui.full_picture;

import dagger.Module;
import dagger.Provides;
import com.frikhi.test.myfbalbum.di.ActivityScope;



@Module
public class FullPictureModule {

    private FullPictureContract.View mView;


    public FullPictureModule(FullPictureContract.View mView) {
        this.mView = mView;
    }

    @ActivityScope
    @Provides
    public FullPictureContract.View provideContext(){
        return mView;
    }

}


