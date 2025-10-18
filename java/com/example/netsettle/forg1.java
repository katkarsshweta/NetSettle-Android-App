package com.example.netsettle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class forg1 extends AppCompatActivity {
    EditText answer;
    TextView question;
    String pno;
    DatabaseReference uref;
    int x;
    String ansdb;
    String ans;
    String quesdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forg1);
        pno = getIntent().getStringExtra("phoneno");
        answer=findViewById(R.id.editTextText3);
        question=findViewById(R.id.textView9);
        uref= FirebaseDatabase.getInstance().getReference().child("users");
        uref.child(pno).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful() && task.getResult().exists()){
                        DataSnapshot ds=task.getResult();
                        quesdb=String.valueOf(ds.child("question").getValue());
                        ansdb=String.valueOf(ds.child("answer").getValue());
                        question.setText(quesdb);

                    }
                else{
                    Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void forg2(View view) {
        ans=answer.getText().toString();
        if(ans.equals(ansdb)){
            Intent change=new Intent(getApplicationContext(), changepass.class);
            change.putExtra("phoneno",pno);
            startActivity(change);
            Toast.makeText(getApplicationContext(),"user identified",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),ans+"invalid answer",Toast.LENGTH_SHORT).show();
        }

    }
}