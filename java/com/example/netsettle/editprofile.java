package com.example.netsettle;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class editprofile extends AppCompatActivity {
    String pno;
    EditText name,pass;
    String n,pas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        Bitmap b= Bitmap.createBitmap(720,1280,Bitmap.Config.ARGB_8888);
        ImageView i=(ImageView) findViewById(R.id.imageView);
        Drawable d = new BitmapDrawable(getResources(), b);
        i.setBackground(d);
        Canvas c=new Canvas(b);

        Paint blue=new Paint();
        blue.setColor(Color.BLUE);

        Paint yellow=new Paint();
        yellow.setColor(Color.rgb(255,215,0));

        Paint darkyellow=new Paint();
        darkyellow.setColor(Color.rgb(184,134,11));

        Paint blackstroke=new Paint();
        blackstroke.setColor(Color.BLACK);
        blackstroke.setStrokeWidth(7);


        Paint red=new Paint();
        red.setColor(Color.RED);
        Paint black=new Paint();
        black.setColor(Color.rgb(139,69,19));
        black.setStyle(Paint.Style.STROKE);
        black.setStrokeWidth(5);

        c.drawCircle(260,300,50,blackstroke);
        c.drawCircle(320,250,50,blackstroke);
        c.drawCircle(400,250,50,blackstroke);
        c.drawCircle(460,300,50,blackstroke);
        c.drawCircle(360,420,140,darkyellow);
        c.drawCircle(360,420,110,yellow);
        c.drawCircle(360,580,30,blackstroke);
        c.drawCircle(360,420,110,black);
        c.drawRect(350,610,370,620,blue);
        c.drawRect(320,620,400,630,blue);
        c.drawRect(290,630,430,640,blue);
        c.drawRect(260,640,460,650,blue);
        c.drawRect(230,650,490,660,blue);
        c.drawRect(200,660,520,670,blue);
        c.drawRect(170,670,550,690,blue);
        c.drawRect(140,690,580,740,blue);

    }

    public void save(View view) {
        pno = getIntent().getStringExtra("phoneno");
        name=findViewById(R.id.editTextText4);
        pass=findViewById(R.id.editTextText5);
        n=name.getText().toString();
        pas=pass.getText().toString();
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users").child(pno);

// Update the name for the user
        usersRef.child("name").setValue(n);
        usersRef.child("pass").setValue(pas);
    }
}