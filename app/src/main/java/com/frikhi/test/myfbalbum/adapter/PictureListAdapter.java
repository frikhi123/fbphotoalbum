package com.frikhi.test.myfbalbum.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.frikhi.test.myfbalbum.R;
import com.frikhi.test.myfbalbum.data.model.Picture;
import com.frikhi.test.myfbalbum.ui.full_picture.FullPictureActivity;
import com.frikhi.test.myfbalbum.util.Constant;


public class PictureListAdapter extends BaseAdapter {

    private List<Picture> pictureList;
    private Context context;

    public List<Picture> getPictureSelectedList() {
        List<Picture> picList = new ArrayList<>();
        for(int i=0;i<pictureList.size();i++){
            if(pictureList.get(i).isSelected())
                picList.add(pictureList.get(i));
        }
        return picList;
    }

    public void setPictureList(List<Picture> pictureList) {
        this.pictureList = pictureList;
    }
    public List<Picture>  getPictureList() {
        return this.pictureList;
    }

    public PictureListAdapter(List<Picture> pictureList, Context context) {
        this.context = context;
        this.pictureList = pictureList;
    }

    @Override
    public int getCount() {
        if(pictureList != null){
            return pictureList.size();
        }else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View mView, ViewGroup parent) {

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.picture_item, null);
        final ImageView picture =  mView.findViewById(R.id.picture);
        final CheckBox selected = mView.findViewById(R.id.selected_pic);

        Glide.with(context)
                .load(pictureList.get(position).getPictureUrl())
                .thumbnail(0.1f)
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .skipMemoryCache(true))
                .into(picture);

        selected.setChecked(pictureList.get(position).isSelected());

        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FullPictureActivity.class);
                intent.putExtra(Constant.Picture_ID, String.valueOf(pictureList.get(position).getId()));
                context.startActivity(intent);
            }
        });

        selected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                pictureList.get(position).setSelected(isChecked);
            }
        });

        return mView;

    }
}
