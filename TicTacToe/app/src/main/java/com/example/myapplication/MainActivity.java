package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Build;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    private long backPressedTime;
    private Toast backToast;
    TextView c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        c = findViewById(R.id.choose);




        ImageView b1 = (ImageView) findViewById(R.id.player1);
        b1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Select.class);
                startActivity(i);
            }
        });

        ImageView b = (ImageView) findViewById(R.id.player2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Game_2.class);
                startActivity(i);
            }
        });

        ImageButton i = (ImageButton) findViewById(R.id.settings);
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Settings.class);
                startActivity(i);
            }
        });

        ImageButton q = (ImageButton) findViewById((R.id.about));
        q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), About.class);
                startActivity(i);
            }
        });
    }

    public void onBackPressed()
        {

            if(backPressedTime+ 2000 > System.currentTimeMillis())
            {
                backToast.cancel();
                super.onBackPressed();
                return;
            }
            else
            {
                backToast= Toast.makeText(getBaseContext(),"Press Back again to exit.",Toast.LENGTH_SHORT);
                backToast.show();
            }

            backPressedTime=System.currentTimeMillis();
        }
    }