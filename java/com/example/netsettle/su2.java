package com.example.netsettle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class su2 extends AppCompatActivity {

    EditText name;
    String n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_su2);
    }

    public static boolean isValid(String n)
    {
        if(n.charAt(0)>='A' && n.charAt(0)<='Z'){
        }
        else{return false;}
        for(int i=1;i<n.length();i++){
            if(n.charAt(i)>='a' && n.charAt(i)<='z' || n.charAt(i)>='A' && n.charAt(i)<='Z'){}
            else{
                if(n.charAt(i)==' '){
                    if(n.charAt(i+1)>='A' && n.charAt(i+1)<='Z'){i++;}
                    else{return false;}
                    }
                }
            }
            return true;
    }
    public void su3(View view) {
        name = (EditText) findViewById(R.id.editTextText);
        n=name.getText().toString();
        if(n.length()==0){
            Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
        }
        else if(isValid(n)==true){
            Intent su3=new Intent(getApplicationContext(),su3.class);
            su3.putExtra("name",n);
            startActivity(su3);
        }
        else{
            Toast.makeText(getApplicationContext(), "Enter Valid Name", Toast.LENGTH_SHORT).show();
        }

    }
}