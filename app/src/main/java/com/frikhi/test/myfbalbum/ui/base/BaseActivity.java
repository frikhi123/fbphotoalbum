package com.frikhi.test.myfbalbum.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;

import butterknife.ButterKnife;
import com.frikhi.test.myfbalbum.R;
import com.frikhi.test.myfbalbum.ui.login.LoginActivity;


public abstract class BaseActivity extends AppCompatActivity {

    private CallbackManager mCallBackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mCallBackManager = CallbackManager.Factory.create();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    public void initView() {
    }


    private void bindViews() {
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }




}
