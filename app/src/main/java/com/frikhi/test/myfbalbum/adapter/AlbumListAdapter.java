package com.frikhi.test.myfbalbum.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.frikhi.test.myfbalbum.data.model.Picture;

import java.util.List;

import com.frikhi.test.myfbalbum.R;
import com.frikhi.test.myfbalbum.data.model.Album;
import com.frikhi.test.myfbalbum.ui.album_pictures.AlbumPicturesActivity;
import com.frikhi.test.myfbalbum.util.Constant;



public class AlbumListAdapter extends RecyclerView.Adapter<AlbumListAdapter.AlbumListViewHolder> {

    private List<Album> albumList;
    private Context context;

    public AlbumListAdapter(List<Album> albumList, Context context) {
        this.albumList = albumList;
        this.context = context;
    }

    @NonNull
    @Override
    public AlbumListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_item, parent, false);

        return new AlbumListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumListViewHolder holder, int position) {

        holder.albumName.setText(albumList.get(position).getAlbumName());
        holder.albumCount.setText(albumList.get(position).getAlbumCount()+" éléments");


        Glide.with(context)
                .load(albumList.get(position).getAlbumCover())
                .thumbnail(0.1f)
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .skipMemoryCache(true))
                .into(holder.albumCover);


    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }


    class AlbumListViewHolder extends RecyclerView.ViewHolder{

        private ImageView albumCover;
        private TextView albumName, albumCount;


        private AlbumListViewHolder(View itemView) {
            super(itemView);
            albumCover = itemView.findViewById(R.id.album_cover);
            albumName = itemView.findViewById(R.id.album_name);
            albumCount = itemView.findViewById(R.id.album_count);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, AlbumPicturesActivity.class);
                    intent.putExtra(Constant.Album_ID,albumList.get(getAdapterPosition()).getAlbumId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
