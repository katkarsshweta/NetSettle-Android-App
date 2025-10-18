
package com.example.netsettle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class changepass extends AppCompatActivity {
    EditText pass,cpass;
    String p,cp;
    String pno;
    DatabaseReference uref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass);


    }

    public void login(View view) {
        pass=(EditText) findViewById(R.id.editTextTextPassword4);
        cpass=(EditText) findViewById(R.id.editTextTextPassword5);
        pno = getIntent().getStringExtra("phoneno");
        p=pass.getText().toString();
        cp=cpass.getText().toString();
        uref = FirebaseDatabase.getInstance().getReference().child("users").child(pno);
        if(cp.equals(p)) {
            uref.child("pass").setValue(p);
            Toast.makeText(getApplicationContext(),cp+"pass",Toast.LENGTH_SHORT).show();
            Intent login = new Intent(getApplicationContext(), login.class);
                        startActivity(login);
        }
        else if(p.length()==0){
            Toast.makeText(getApplicationContext(),"enter pass",Toast.LENGTH_SHORT).show();
        }
        else if(cp.length()==0){
            Toast.makeText(getApplicationContext(),"confirm pass",Toast.LENGTH_SHORT).show();
        }
        else if(!p.equals(cp)){
            Toast.makeText(getApplicationContext(),"confirmed pass doesnt match",Toast.LENGTH_SHORT).show();
        }

    }
}