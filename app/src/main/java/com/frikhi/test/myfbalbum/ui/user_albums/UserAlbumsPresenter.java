package com.frikhi.test.myfbalbum.ui.user_albums;

import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import com.frikhi.test.myfbalbum.data.model.Album;




public class UserAlbumsPresenter implements UserAlbumsContract.Presenter{

    UserAlbumsContract.View mView;

    @Inject
    public UserAlbumsPresenter(UserAlbumsContract.View mView) {
        this.mView = mView;
    }


    @Override
    public void getAlbums(String userId) {
        new GraphRequest(
                AccessToken.getCurrentAccessToken(), "/"+userId+"/albums?fields=name,count,picture",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        if(response != null){
                            extractAlbumsData(response);
                        }
                    }
                }
        ).executeAsync();
    }

    @Override
    public void extractAlbumsData(GraphResponse response) {
        try {
        JSONObject dataResponse = response.getJSONObject();
        JSONArray data = null;
            data = dataResponse.getJSONArray("data");
            Log.i("album_list_FB",data.toString());
            List<Album> albumList = new ArrayList<>();
            for(int i = 0; i < data.length(); i++){
                albumList.add(new Album(
                        data.getJSONObject(i).getString("id"),
                        data.getJSONObject(i).getString("name"),
                        data.getJSONObject(i).getJSONObject("picture").getJSONObject("data").getString("url"),
                        data.getJSONObject(i).getString("count")
                ));
            }

            //Sorted By Name
            Collections.sort(albumList, new Comparator<Album>() {
                @Override
                public int compare(Album o1, Album o2) {
                    return o1.getAlbumName().compareTo(o2.getAlbumName());
                }
            });

            mView.showAlbumList(albumList);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
