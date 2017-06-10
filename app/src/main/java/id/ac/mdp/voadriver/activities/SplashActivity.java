package id.ac.mdp.voadriver.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import id.ac.mdp.voadriver.utils.Utilities;

public class SplashActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().post(new Runnable() {
            @Override
            public void run() {
            Utilities util=new Utilities();
                if (util.isLogin(SplashActivity.this)) {
//                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
                finish();
            }
        });
    }
}

