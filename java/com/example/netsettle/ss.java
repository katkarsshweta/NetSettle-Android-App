package com.example.netsettle;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class ss extends AppCompatActivity {
    MediaPlayer mp1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ss);
        mp1= MediaPlayer.create(this,R.raw.audio);
        mp1.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent main = new Intent(ss.this, logsignup.class);
                startActivity(main);
                mp1.stop();
            }
        }, 6000);
    }
}