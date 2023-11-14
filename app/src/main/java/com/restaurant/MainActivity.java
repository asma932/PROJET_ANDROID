package com.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.graphics.*;

import android.os.*;
import android.view.*;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    private Timer _timer = new Timer();


    private Intent in = new Intent();
    private TimerTask tme;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeLogic();
    }


    private void initializeLogic() {
        _status_bar_color("", "#ffffff");
        tme = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        in.setClass(getApplicationContext(), HomeActivity.class);
                        startActivity(in);
                        finish();
                    }
                });
            }
        };
        _timer.schedule(tme, (int)(5000));
    }

    public void _status_bar_color(final String _colour1, final String _colour2) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            Window w = this.getWindow(); w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setNavigationBarColor(Color.parseColor(_colour2));
        }
    }


}