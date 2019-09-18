package com.frikhi.test.myfbalbum.ui.album_pictures;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import com.frikhi.test.myfbalbum.App;
import com.frikhi.test.myfbalbum.R;
import com.frikhi.test.myfbalbum.adapter.PictureListAdapter;
import com.frikhi.test.myfbalbum.data.model.Picture;
import com.frikhi.test.myfbalbum.ui.base.BaseActivity;
import com.frikhi.test.myfbalbum.util.Constant;
import com.frikhi.test.myfbalbum.util.ImagesDownloader;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;


public class AlbumPicturesActivity extends BaseActivity implements AlbumPicturesContract.View{

    @BindView(R.id.picture_list_save)
    Button pictureListSave;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.progress_horizontal)
    ProgressBar progress_horizontal;

    @BindView(R.id.picture_grid)
    GridView pictureGrid;

    @Inject
    AlbumPicturesPresenter presenter;

    String albumId;
    PictureListAdapter pictureListAdapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_pictures);
        context = this;

        DaggerAlbumPicturesComponent.builder()
                .appComponent(((App)getApplicationContext()).getAppComponent())
                .albumPicturesModule(new AlbumPicturesModule(this))
                .build()
                .inject(this);

        albumId = getIntent().getStringExtra(Constant.Album_ID);
        if(albumId != null){
            presenter.getAlbumPictures(albumId);
        }

        pictureListSave.setOnClickListener(saveListener);
    }


    @Override
    public void showPictureGrid(List<Picture> listPicture) {
        pictureListAdapter = new PictureListAdapter(listPicture,this);
        pictureGrid.setAdapter(pictureListAdapter);
    }


    public Bitmap getBitmapFromURL(String imageUrl) {
        Bitmap bitmap = null;
        HttpURLConnection connection = null;
        InputStream is = null;
        ByteArrayOutputStream out = null;
        try {
            connection = (HttpURLConnection) new URL(imageUrl).openConnection();

            connection.connect();
            final int length = connection.getContentLength();
            if (length <= 0) {
                return null;
            }
            is = new BufferedInputStream(connection.getInputStream(), 8192);
            out = new ByteArrayOutputStream();
            byte bytes[] = new byte[8192];
            int count;
            long read = 0;
            while ((count = is.read(bytes)) != -1) {
                read += count;
                out.write(bytes, 0, count);
            }
            bitmap = BitmapFactory.decodeByteArray(out.toByteArray(), 0, out.size());

        } catch (Throwable e) {
            return null;
        } finally {
            try {
                if (connection != null)
                    connection.disconnect();
                if (out != null) {
                    out.flush();
                    out.close();
                }
                if (is != null)
                    is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    public File saveBitmapToGallery(Bitmap bitmap) {
        File myDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), getString(R.string.app_name));
        if (!myDir.exists()) {
            myDir.mkdirs();
        }
        String filename = getString(R.string.app_name) + "_" + Calendar.getInstance().getTimeInMillis() + ".png";
        final File file = new File(myDir, filename);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            MediaScannerConnection.scanFile(AlbumPicturesActivity.this, new String[]{file.getAbsolutePath()}, null, null);

            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    View.OnClickListener saveListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            if(pictureListAdapter != null && pictureListAdapter.getPictureSelectedList().size()!=0){
                Dexter.withActivity(AlbumPicturesActivity.this).withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE).withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            new SavePictures(context,pictureListAdapter.getPictureSelectedList()).execute();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show();
                    }


                }).check();
            }else {
                Snackbar.make(pictureListSave, getString(R.string.save_warning), Snackbar.LENGTH_LONG).show();
            }
        }
    };


    private class SavePictures extends AsyncTask<Void, Void, Void> {

        Context context;
        List<Picture> pictureList;

        private SavePictures(Context context,List<Picture>pictureList) {
            this.context = context;
            this.pictureList = pictureList;
        }


        protected void onPreExecute() {
            super.onPreExecute();
            progress_horizontal.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            pictureListSave.setVisibility(View.GONE);
            progress_horizontal.setProgress(0);

        }

        protected Void doInBackground(Void... params) {
            for(int i=0;i<pictureList.size();i++){
                progress_horizontal.setProgress(i*100/pictureList.size());
                saveBitmapToGallery(getBitmapFromURL(pictureList.get(i).getPictureUrl()));
//                ImagesDownloader.writeToDisk(AlbumPicturesActivity.this,pictureList.get(i).getPictureUrl(),null);
            }

            return null;
        }

        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progress_horizontal.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            pictureListSave.setVisibility(View.VISIBLE);
            Snackbar.make(pictureListSave, pictureList.size()+" "+getString(R.string.saved_successfully), Snackbar.LENGTH_LONG).show();
        }

    }
}
