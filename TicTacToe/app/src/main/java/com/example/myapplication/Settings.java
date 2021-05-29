package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class Settings extends AppCompatActivity {
    AudioManager audioManager;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Switch soundSwitch;
    Button reset1,reset2;
    TextView rules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        audioManager = (AudioManager)getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        soundSwitch = findViewById(R.id.soundswitch);

        soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    audioManager.setStreamMute(AudioManager.STREAM_MUSIC,false);
                    sharedPreferences =  getPreferences(Context.MODE_PRIVATE);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("sound", true);
                    editor.apply();

                } else {
                    audioManager.setStreamMute(AudioManager.STREAM_MUSIC,true);
                    sharedPreferences =  getPreferences(Context.MODE_PRIVATE);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("sound", false);
                    editor.apply();
                }
            }
        });

        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        boolean isChecked = sharedPreferences.getBoolean("sound", true);
        soundSwitch.setChecked(isChecked);
    }

}
