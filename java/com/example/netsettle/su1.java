package com.example.netsettle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class su1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_su1);
    }
    public void su2(View view) {
        Intent su2=new Intent(getApplicationContext(),su2.class);
        startActivity(su2);
    }
}