package com.example.lenovo.task;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by LENOVO on 26-12-2017.
 */

public class SplashScreen extends Activity {
    Handler handler;
    SharedPreferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        handler=new Handler();
        preferences= PreferenceManager.getDefaultSharedPreferences(this);

        handler.postDelayed(new Runnable() {
            boolean pre = false;
            @Override
            public void run() {

                if(preferences!=null) {

                    Intent intent=new Intent(SplashScreen.this,Activity1.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);

                }
                finish();
            }
        },5000);
    }
}
