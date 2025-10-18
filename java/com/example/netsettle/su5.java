package com.example.netsettle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class su5 extends AppCompatActivity {
    EditText password;
    EditText cpassword;
    String n;
    String pno;
    String pass;
    String cpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_su5);

    }

    public static boolean isValid(String pass, String cpass)
    {
        for(int i=0;i<pass.length();i++){
            if(pass.charAt(i)==cpass.charAt(i)){}
            else{
                return false;
            }
        }
        return true;
    }

    public void su5(View view) {
        n = getIntent().getStringExtra("name");
        pno = getIntent().getStringExtra("phoneno");
        password = (EditText) findViewById(R.id.editTextTextPassword);
        cpassword = (EditText) findViewById(R.id.editTextTextPassword3);
        pass=password.getText().toString();
        cpass=cpassword.getText().toString();

        if(pass.length()==0){
            Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();
        }
        else if(cpass.length()==0){
            Toast.makeText(getApplicationContext(), "Please confirm password", Toast.LENGTH_SHORT).show();
        }
        else if(pass.length()<8){
            Toast.makeText(getApplicationContext(), "Password must be atleast 8 lettered", Toast.LENGTH_SHORT).show();
        }
        else if(isValid(pass,cpass)==true){
            Intent su6=new Intent(getApplicationContext(),su6.class);
            su6.putExtra("name",n);
            su6.putExtra("phoneno",pno);
            su6.putExtra("password",pass);
            startActivity(su6);
        }
        else{
            Toast.makeText(getApplicationContext(), "Enter Valid contact number", Toast.LENGTH_SHORT).show();
        }
    }


}