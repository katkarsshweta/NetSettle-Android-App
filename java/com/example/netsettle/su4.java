package com.example.netsettle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class su4 extends AppCompatActivity {

    EditText phno;
    String n;
    String pno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_su4);
    }

    public static boolean isValid(String pno)
    {
        if(pno.length()!=10){
            return false;
        }

        for(int i=0;i<pno.length();i++){
            if(pno.charAt(i)>='0' && pno.charAt(i)<='9'){}
            else{
                return false;
                }
            }
        return true;
    }
    public void su4(View view) {
        phno = (EditText) findViewById(R.id.editTextPhone2);
        n = getIntent().getStringExtra("name");
        pno=phno.getText().toString();

        if(pno.length()==0){
            Toast.makeText(getApplicationContext(), "Please enter contact number", Toast.LENGTH_SHORT).show();
        }
        else if(isValid(pno)==true){
            Intent su5=new Intent(getApplicationContext(),su5.class);
            su5.putExtra("name",n);
            su5.putExtra("phoneno",pno);
            startActivity(su5);
        }
        else{
            Toast.makeText(getApplicationContext(), "Enter Valid contact number", Toast.LENGTH_SHORT).show();
        }
    }
}