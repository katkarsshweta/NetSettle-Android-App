package com.example.netsettle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class page2 extends AppCompatActivity {

    String pno;

    DatabaseReference myRef;
    BottomNavigationView bottomNavigationView;
    private TextView netBalanceTextView;
    private TextView totalInTextView;
    private TextView totalOutTextView;


    private int netBalance;
    private int totalIn;
    private int totalOut;

    ////
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference usersRef ;
    private ActivityResultLauncher<Intent> startPage3ForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    int amount = data.getIntExtra("amount", 0);
                    netBalance += amount;
                    totalIn += amount;
                    updateTextFields();
                }
            }
    );

    private ActivityResultLauncher<Intent> startPage4ForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    int amount = data.getIntExtra("amount", 0);
                    netBalance -= amount;
                    totalOut += amount;
                    updateTextFields();
                }
            }
    );
    ////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);



        pno=getIntent().getStringExtra("phoneno");
        netBalanceTextView = findViewById(R.id.netBalanceTextView);
        totalInTextView = findViewById(R.id.totalInTextView);
        totalOutTextView = findViewById(R.id.totalOutTextView);

        updateTextFields();


        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnItemSelectedListener(item -> {

            if(item.getItemId() == R.id.personal)
            {
                Intent personal=new Intent(getApplicationContext(), page2.class);
                personal.putExtra("phoneno",pno);
                startActivity(personal);

            }
            else if(item.getItemId() == R.id.profile)
            {
                //replaceFragment(new profileFragment(),pno);
                Intent personal=new Intent(getApplicationContext(), MainActivity.class);
                personal.putExtra("phoneno",pno);
                startActivity(personal);

            }

            return true;
        });

    }
//    private void replaceFragment(Fragment fragment, String pno) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        Bundle bundle = new Bundle();
//        bundle.putString("phoneno", pno);
//        fragment.setArguments(bundle);
//
//        fragmentTransaction.replace(R.id.frame_layout, fragment);
//        fragmentTransaction.commit();
//    }

    public void onCashInButtonClick(View view) {
        // Handle Cash In logic
        // Update netBalance and totalIn
        Intent intent = new Intent(this, page3.class);
        intent.putExtra("phoneno",pno);
        startActivityForResult(intent, 1); // Request code 1 for CashIn
        updateTextFields();
    }



    public void onCashOutButtonClick(View view) {
        // Handle Cash Out logic
        // Update netBalance and totalOut
        Intent intent = new Intent(this, page4.class);
        intent.putExtra("phoneno",pno);
       startActivityForResult(intent, 2); // Request code 2 for CashOut
        updateTextFields();
    }


    private void updateTextFields() {


        if (netBalance < 0) {
            netBalance = 0;
        }
        if (totalIn < 0) {
            totalIn = 0;
        }
        if (totalOut < 0) {
            totalOut = 0;
        }
        netBalanceTextView.setText("Net Balance: " + netBalance);
        totalInTextView.setText("Total In: " + totalIn);
        totalOutTextView.setText("Total Out: " + totalOut);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            int amount = data.getIntExtra("amount", 0);
            if (requestCode == 1) {
                // Handle result from Page3 (CashIn)
                netBalance += amount;
                totalIn += amount;
                myRef= FirebaseDatabase.getInstance().getReference().child("users").child(pno);
                myRef.child("netbalance").setValue(netBalance);
                myRef.child("totalIn").setValue(totalIn);


            } else if (requestCode == 2) {
                // Handle result from Page4 (CashOut)
                netBalance -= amount;
                totalOut += amount;

                myRef= FirebaseDatabase.getInstance().getReference().child("users").child(pno);
                myRef.child("netbalance").setValue(netBalance);
                myRef.child("TotalOut").setValue(totalOut);
            }
            updateTextFields();
        }
    }
}
