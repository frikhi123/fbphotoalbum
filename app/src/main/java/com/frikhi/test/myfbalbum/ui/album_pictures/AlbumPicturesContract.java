package com.frikhi.test.myfbalbum.ui.album_pictures;

import com.facebook.GraphResponse;

import java.util.List;

import com.frikhi.test.myfbalbum.data.model.Picture;



public class AlbumPicturesContract {

    interface View {
        void showPictureGrid(List<Picture> listPicture);

    }

    interface Presenter {
        void getAlbumPictures(String AlbumId);
        void extractAlbumPicturesData(GraphResponse response);
    }
}
