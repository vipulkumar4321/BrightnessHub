package com.example.brightnesshub;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.util.Log;
import android.content.Context;
import android.provider.Settings;


public class MainActivity extends AppCompatActivity {

    public void sunClicked(View view) {
        WindowManager.LayoutParams layout = getWindow().getAttributes();
        layout.screenBrightness = 1F;
        getWindow().setAttributes(layout);
    }

    public void moonClicked(View view) {
        WindowManager.LayoutParams layout = getWindow().getAttributes();
        layout.screenBrightness = 0.1F;
        getWindow().setAttributes(layout);
    }

    public void cloudyClicked(View view) {
        WindowManager.LayoutParams layout = getWindow().getAttributes();
        layout.screenBrightness = 0.5F;
        getWindow().setAttributes(layout);
    }

    public void autoClicked(View view) {

        Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
    }

    public void energyClicked(View view) {
        WindowManager.LayoutParams layout = getWindow().getAttributes();
        layout.screenBrightness = 0.35F;
        getWindow().setAttributes(layout);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SeekBar seekBarBrightness = findViewById(R.id.seekBarBrightness);
        seekBarBrightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                Context context = getApplicationContext();
                boolean canWriteSettings = Settings.System.canWrite(context);

                if (canWriteSettings) {


                    int screenBrightnessValue = (progress*255)/100;


                    Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
                    Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, screenBrightnessValue);

                } else {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                    context.startActivity(intent);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        int currBrightness = Settings.System.getInt(getContentResolver(),Settings.System.SCREEN_BRIGHTNESS,0);
        seekBarBrightness.setProgress(currBrightness);



//        final Handler handler = new Handler();
//        Runnable run = new Runnable() {
//            @Override
//            public void run() {
//
//                int currBrightness = Settings.System.getInt(getContentResolver(),Settings.System.SCREEN_BRIGHTNESS,0);
//                seekBarBrightness.setProgress(currBrightness);
//
//                handler.postDelayed(this, 1000);
//            }
//        };
//        handler.post(run);

    }
}
