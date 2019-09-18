package com.frikhi.test.myfbalbum.data.model;

import java.util.ArrayList;
import java.util.List;


public class Picture {

    String id;
    String pictureUrl;
    boolean isSelected;

    public Picture() {

    }

    public Picture(String id, String pictureUrl,Boolean isSelected) {
        this.id = id;
        this.pictureUrl = pictureUrl;
        this.isSelected = isSelected;
    }

    public String getId() {
        return id;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public ArrayList<String> getPictureUrlList(List<Picture> pics) {

        ArrayList<String> pictureList = new ArrayList<>();
        for(int i=0;i<pics.size();i++){
            pictureList.add(pics.get(i).getPictureUrl());
        }
        return pictureList;
    }

}
