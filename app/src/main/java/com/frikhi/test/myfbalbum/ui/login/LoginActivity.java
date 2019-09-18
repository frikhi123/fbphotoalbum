package com.frikhi.test.myfbalbum.ui.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.frikhi.test.myfbalbum.App;
import com.frikhi.test.myfbalbum.R;
import com.frikhi.test.myfbalbum.ui.user_albums.UserAlbumsActivity;
import com.frikhi.test.myfbalbum.util.Constant;


public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    @BindView(R.id.login_button)
    LoginButton loginButton;

    @BindView(R.id.github_link)
    TextView github_link;

    @Inject
    LoginPresenter presenter;

    CallbackManager callbackManager;
    AccessToken accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        DaggerLoginComponent.builder()
                .appComponent(((App)getApplicationContext()).getAppComponent())
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);

        callbackManager = CallbackManager.Factory.create();
        accessToken = AccessToken.getCurrentAccessToken();

        if(accessToken != null && !accessToken.isExpired()){
            loginSuccess(accessToken.getUserId());
        }else {
            loginButton.setReadPermissions("user_photos");
            presenter.loginClickCallBack(loginButton,callbackManager);
        }

        github_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Intent.createChooser(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.github_link))), "Choose browser"));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void loginSuccess(String userId) {
        Intent intent = new Intent(LoginActivity.this,UserAlbumsActivity.class);
        intent.putExtra(Constant.User_ID,userId);
        startActivity(intent);
    }

    @Override
    public void loginFailed() {
        Toast.makeText(getApplicationContext(),"Login Failed", Toast.LENGTH_SHORT).show();
    }
}
