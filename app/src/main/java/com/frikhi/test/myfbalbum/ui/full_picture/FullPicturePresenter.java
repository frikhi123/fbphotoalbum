package com.frikhi.test.myfbalbum.ui.full_picture;



import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;




public class FullPicturePresenter implements FullPictureContract.Presenter{

    FullPictureContract.View mView;

    @Inject
    public FullPicturePresenter(FullPictureContract.View mView) {
        this.mView = mView;
    }


    @Override
    public void getPicture(String pictureId) {
        new GraphRequest(
                AccessToken.getCurrentAccessToken(), "/"+pictureId+"/?fields=images",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        if(response != null){
                            extractPictureData(response);
                        }
                    }
                }
        ).executeAsync();
    }

    @Override
    public void extractPictureData(GraphResponse response) {
        try {
            JSONObject dataResponse = response.getJSONObject();
            JSONArray data = dataResponse.getJSONArray("images");
            mView.showPicture(data.getJSONObject(0).getString("source"));
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
