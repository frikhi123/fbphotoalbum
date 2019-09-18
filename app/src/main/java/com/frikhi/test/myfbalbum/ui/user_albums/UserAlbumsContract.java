package com.frikhi.test.myfbalbum.ui.user_albums;

import com.facebook.GraphResponse;

import java.util.List;

import com.frikhi.test.myfbalbum.data.model.Album;



public class UserAlbumsContract {

    interface View {
        void showAlbumList(List<Album> listAlbum);

    }

    interface Presenter {
        void getAlbums(String userId);
        void extractAlbumsData(GraphResponse response);
    }
}
