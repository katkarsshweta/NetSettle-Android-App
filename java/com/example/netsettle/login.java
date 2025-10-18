package com.example.netsettle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class login extends AppCompatActivity {
    EditText userpno,userpass;
    String upno,upass;
    DatabaseReference uref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void home(View view) {
        userpno=findViewById(R.id.editTextPhone);
        userpass=findViewById(R.id.editTextTextPassword2);
        upno=userpno.getText().toString();
        upass=userpass.getText().toString();
        uref= FirebaseDatabase.getInstance().getReference().child("users");
        uref.child(upno).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()){
                    if(task.getResult().exists()){
                        DataSnapshot ds=task.getResult();
                        String passdb=String.valueOf(ds.child("pass").getValue());
                        if(passdb.equals(upass)){
                            Toast.makeText(getApplicationContext(),"Logging in ",Toast.LENGTH_SHORT).show();
                            Intent home=new Intent(getApplicationContext(),MainActivity.class);
                            home.putExtra("phoneno",upno);
                            startActivity(home);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Invalid pass",Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"User doesn't exist",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void forg(View view) {
        userpno=findViewById(R.id.editTextPhone);
        upno=userpno.getText().toString();
        if(upno.isEmpty()){
            Toast.makeText(getApplicationContext(),"enter phone no",Toast.LENGTH_SHORT).show();
        }
        else {
            Intent forg1 = new Intent(getApplicationContext(), forg1.class);
            forg1.putExtra("phoneno", upno);
            startActivity(forg1);
        }
    }
}