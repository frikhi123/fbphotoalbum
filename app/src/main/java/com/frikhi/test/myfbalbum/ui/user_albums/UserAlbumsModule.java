package com.frikhi.test.myfbalbum.ui.user_albums;

import dagger.Module;
import dagger.Provides;
import com.frikhi.test.myfbalbum.di.ActivityScope;



@Module
public class UserAlbumsModule {

    private UserAlbumsContract.View mView;


    public UserAlbumsModule(UserAlbumsContract.View mView) {
        this.mView = mView;
    }

    @ActivityScope
    @Provides
    public UserAlbumsContract.View provideContext(){
        return mView;
    }

}


