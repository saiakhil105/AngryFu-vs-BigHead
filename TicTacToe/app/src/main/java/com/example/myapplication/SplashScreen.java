package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    private static int time_out = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

                ImageView img,img2;
                TextView txt;

                img=findViewById(R.id.img_bh);
                img2=findViewById(R.id.img_bh);
                txt=findViewById(R.id.textView);


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent=new Intent(SplashScreen.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },time_out);

            }
}