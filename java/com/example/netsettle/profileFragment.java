package com.example.netsettle;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profileFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference usersRef ;
    TextView name;
    String n;
    TextView phnoset;
    String pno;

    View v;
    Button b;
    public profileFragment() {
        // Required empty public constructor
    }

    public static profileFragment newInstance(String param1, String param2) {
        profileFragment fragment = new profileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.profile, container, false);
        name=v.findViewById(R.id.textView10);
        phnoset=v.findViewById(R.id.textView11);
        b=v.findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editprofile=new Intent(v.getContext(), editprofile.class);
                editprofile.putExtra("phoneno",pno);
                startActivity(editprofile);
            }
        });

        Bitmap b= Bitmap.createBitmap(720,1280,Bitmap.Config.ARGB_8888);
        ImageView i=v.findViewById(R.id.imageView);
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

        // Get the passed string data
        pno = getArguments().getString("phoneno");

        usersRef  = database.getReference().child("users").child(pno);
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String userName = dataSnapshot.child("name").getValue(String.class);
                    name.setText(userName);
                    phnoset.setText(pno);

                } else {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors that occurred while fetching the data
            }
        });
        return v;


    }




}