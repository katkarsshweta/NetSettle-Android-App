package com.example.netsettle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class logsignup extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logsignup);
    }
    public void no(View view) {
        Intent login=new Intent(getApplicationContext(),login.class);
        startActivity(login);
    }
    public void yes(View view) {
        Intent su1=new Intent(getApplicationContext(),su1.class);
        startActivity(su1);
    }


}