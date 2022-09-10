package com.example.sqlcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences(WelcomeLoginPage.MyPREFERENCES, MODE_PRIVATE);
                boolean hasLoggedIn = sharedPreferences.getBoolean("hasLoggedIn", false);
                if (hasLoggedIn){
                    startActivity(new Intent(SplashScreen.this, WelcomeLoginPage.class));
                }else{
                    startActivity(new Intent(SplashScreen.this, LoginPage.class));
                }
                finish();
            }
        },2000);
    }
}