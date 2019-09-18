package com.frikhi.test.myfbalbum.ui.full_picture;

import com.facebook.GraphResponse;



public class FullPictureContract {

    interface View {
        void showPicture(String pictureUrl);
    }

    interface Presenter {
        void getPicture(String pictureId);
        void extractPictureData(GraphResponse response);
    }
}
