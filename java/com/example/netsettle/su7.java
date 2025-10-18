package com.example.netsettle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class su7 extends AppCompatActivity {

    String n;
    String pno;
    String pass;
    String ques;
    String ans;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_su7);
        n = getIntent().getStringExtra("name");
        pno = getIntent().getStringExtra("phoneno");
        pass = getIntent().getStringExtra("password");
        ques = getIntent().getStringExtra("question");
        ans = getIntent().getStringExtra("answer");
    }

    public void go(View view) {
        myRef =FirebaseDatabase.getInstance().getReference().child("users").child(pno);
        myRef.child("name").setValue(n);
        myRef.child("pass").setValue(pass);
        myRef.child("question").setValue(ques);
        myRef.child("answer").setValue(ans);
        Intent su7=new Intent(getApplicationContext(),login.class);
        startActivity(su7);


    }
}