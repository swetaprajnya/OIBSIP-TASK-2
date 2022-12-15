package com.sweta.stopwatch1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    Chronometer chronometer;
    ImageButton btStart,btstop;

    private boolean isResume;
    Handler handler;
    long tMillisec,tStart,tBuff,tUpdate = 0L;
    int sec,min,millisec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.chronometer);
        btStart = findViewById(R.id.bt_start);
        btstop = findViewById(R.id.bt_stop);

        handler = new Handler();

        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isResume) {
                    tStart = SystemClock.uptimeMillis();
                    handler.postDelayed(runnable, 0);
                    chronometer.start();
                    isResume = true;
                    btstop.setVisibility(View.GONE);
                    btStart.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_pause_circle
                    ));

                }else{
                    tBuff += tMillisec;
                    handler.removeCallbacks(runnable);
                    chronometer.stop();
                    isResume = false;
                    btstop.setVisibility(view.VISIBLE);
                    btStart.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_play));

                    }

                }

        });

        btstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isResume){
                    btStart.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_play));
                    tMillisec = 0L;
                    tStart = 0L;
                    tBuff = 0L;
                    tUpdate = 0L;
                    sec = 0;
                    min = 0;
                    millisec = 0;
                    chronometer.setText("00:00:00");
                }
            }
        });
    }

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            tMillisec = SystemClock.uptimeMillis() - tStart;
            tUpdate = tBuff + tMillisec;
            sec = (int) (tUpdate / 1000);
            min = sec / 60;
            sec = sec % 60;
            millisec = (int) (tUpdate % 100);
            chronometer.setText(String.format("%02d", min) + ":"
                    + String.format("%02d", sec) + ":" + String.format("%02d", millisec));
            handler.postDelayed(this, 60);

        }
    };


    }
