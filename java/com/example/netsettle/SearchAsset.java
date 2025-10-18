package com.example.netsettle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class SearchAsset extends AppCompatActivity {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    // Reference to the "users" node
    private DatabaseReference usersRef ;
EditText name;
EditText cat;
TextView nam,type,quant,date,time,loc,rem;
String sname,scat,rnam,rtype,rquant,rdate,rtime,rloc,rrem,pno,doc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_asset);
        name=(EditText) findViewById(R.id.EnterAssetName);

        cat=(EditText) findViewById(R.id.CategoryAsset);

        nam=(TextView) findViewById(R.id.textAssetName);

        type=(TextView)findViewById(R.id.textAssetType);
        quant=(TextView)findViewById(R.id.textAssetQuantity);
        date=(TextView)findViewById(R.id.textAssetDate);
        time=(TextView)findViewById(R.id.textAssetTime);
        loc=(TextView)findViewById(R.id.textAssetLocation);
        rem=(TextView)findViewById(R.id.textAssetRemark);
        pno = getIntent().getStringExtra("phoneno");


    }

    public void search(View view) {
        sname=name.getText().toString();
        scat=cat.getText().toString();
        usersRef=database.getReference("users").child(pno).child("asset").child(scat).child(sname);
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(getApplicationContext(),"Your results are displayed", Toast.LENGTH_SHORT).show();
                    rtype = dataSnapshot.child("Type").getValue(String.class);
                    rquant = dataSnapshot.child("Quantity").getValue(String.class);
                    rdate = dataSnapshot.child("Date").getValue(String.class);
                    rtime = dataSnapshot.child("Time").getValue(String.class);
                    rloc = dataSnapshot.child("Location").getValue(String.class);
                    rrem = dataSnapshot.child("Remark").getValue(String.class);
                    doc=dataSnapshot.child("document").getValue(String.class);
                    nam.setText(doc);
                    type.setText(rtype);
                    quant.setText(rquant);
                    date.setText(rdate);
                    time.setText(rtime);
                    loc.setText(rloc);
                    rem.setText(rrem);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //nam.setText("Shreya");

    }

    public void doc(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(doc));
        startActivity(intent);
    }
}