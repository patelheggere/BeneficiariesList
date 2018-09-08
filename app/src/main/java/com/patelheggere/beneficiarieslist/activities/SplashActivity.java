package com.patelheggere.beneficiarieslist.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.patelheggere.beneficiarieslist.R;
import com.patelheggere.beneficiarieslist.util.SharedPrefsHelper;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if(SharedPrefsHelper.getInstance().get("FIRST_TIME", true)) {
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                }
                else {
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                }

                // close this activity
                finish();

            }



        }, SPLASH_TIME_OUT);
    }
}
