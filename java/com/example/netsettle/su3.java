package com.example.netsettle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class su3 extends AppCompatActivity {

    String n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_su3);
        n = getIntent().getStringExtra("name");
    }


    public void su4(View view) {
        Intent su4=new Intent(getApplicationContext(),su4.class);
        su4.putExtra("name",n);
        startActivity(su4);
    }
}