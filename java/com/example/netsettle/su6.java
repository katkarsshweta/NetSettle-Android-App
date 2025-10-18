package com.example.netsettle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class su6 extends AppCompatActivity {
    Spinner question;
    EditText answer;
    String n;
    String pno;
    String pass;
    String ques;
    String ans;
    Spinner s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_su6);

        question = findViewById(R.id.spinner);
       question.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(position){
            case 0:
                ques="nothing";
                break;
            case 1:
                ques="What is the name of your best friend?";
                break;
            case 2:
                ques="What is your favourite color?";
                break;
            case 3:
                ques="what is your dream destination?";
                break;
            case 4:
                ques="What is the name of your favourite teacher?";
                break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});
    }

    public void su6(View view) {

        answer = (EditText) findViewById(R.id.editTextText2);
        n = getIntent().getStringExtra("name");
        pno = getIntent().getStringExtra("phoneno");
        pass = getIntent().getStringExtra("password");
        ans=answer.getText().toString();

        if(ques=="nothing"){
            Toast.makeText(getApplicationContext(), "Please select question", Toast.LENGTH_SHORT).show();
        }
        else if(answer.length()==0){
            Toast.makeText(getApplicationContext(), "Please answer the question", Toast.LENGTH_SHORT).show();
        }
        else if(pass.length()<8){
            Toast.makeText(getApplicationContext(), "Password must be atleast 8 lettered", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent su7=new Intent(getApplicationContext(),su7.class);
            su7.putExtra("name",n);
            su7.putExtra("phoneno",pno);
            su7.putExtra("password",pass);
            su7.putExtra("question",ques);
            su7.putExtra("answer",ans);
            startActivity(su7);
        }
    }
}
