package com.frikhi.test.myfbalbum.ui.user_albums;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import com.frikhi.test.myfbalbum.App;
import com.frikhi.test.myfbalbum.R;
import com.frikhi.test.myfbalbum.adapter.AlbumListAdapter;
import com.frikhi.test.myfbalbum.data.model.Album;
import com.frikhi.test.myfbalbum.ui.base.BaseActivity;
import com.frikhi.test.myfbalbum.util.Constant;

public class UserAlbumsActivity extends BaseActivity implements UserAlbumsContract.View {

    @BindView(R.id.album_list) RecyclerView albumList;

    @Inject
    UserAlbumsPresenter presenter;


    String userId;
    AlbumListAdapter albumListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_albums);

        DaggerUserAlbumsComponent.builder()
                .appComponent(((App)getApplicationContext()).getAppComponent())
                .userAlbumsModule(new UserAlbumsModule(this))
                .build()
                .inject(this);


        userId = getIntent().getStringExtra(Constant.User_ID);
        if(userId != null){
            presenter.getAlbums(userId);
        }


    }

    @Override
    public void showAlbumList(List<Album> listAlbum) {
        albumListAdapter = new AlbumListAdapter(listAlbum, this);
        LinearLayoutManager layoutManager =  new GridLayoutManager(getApplicationContext(), 2);
        albumList.setLayoutManager(layoutManager);
        albumList.setAdapter(albumListAdapter);
    }




}
