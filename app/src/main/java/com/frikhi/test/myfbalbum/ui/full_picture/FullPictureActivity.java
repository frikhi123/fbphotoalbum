package com.frikhi.test.myfbalbum.ui.full_picture;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import javax.inject.Inject;

import butterknife.BindView;
import com.frikhi.test.myfbalbum.App;
import com.frikhi.test.myfbalbum.R;
import com.frikhi.test.myfbalbum.ui.base.BaseActivity;
import com.frikhi.test.myfbalbum.util.Constant;
import com.myandroid.views.MultiTouchListener;

public class FullPictureActivity extends BaseActivity implements FullPictureContract.View {

    @BindView(R.id.full_picture)
    ImageView fullPicture;

    @Inject
    FullPicturePresenter presenter;

    String pictureId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_full_picture);

        DaggerFullPictureComponent.builder()
                .appComponent(((App)getApplicationContext()).getAppComponent())
                .fullPictureModule(new FullPictureModule(this))
                .build()
                .inject(this);

        pictureId = getIntent().getStringExtra(Constant.Picture_ID);
        if(pictureId != null){
            presenter.getPicture(pictureId);
        }

        fullPicture.setOnTouchListener(moveListener);

    }

    @Override
    public void showPicture(String pictureUrl) {
        Glide.with(this)
                .load(pictureUrl)
                .thumbnail(0.1f)
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .skipMemoryCache(true))
                .into(fullPicture);

    }


    MultiTouchListener moveListener = new MultiTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            return super.onTouch(view, event);
        }
    };
}
