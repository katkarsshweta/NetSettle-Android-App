package com.example.netsettle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
public class MainActivity extends AppCompatActivity {

    DatabaseReference myRef;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    String pno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main );

        pno = getIntent().getStringExtra("phoneno");

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationview = findViewById(R.id.nav_view);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new personalFragment()).commit();
            navigationview.setCheckedItem(R.id.nav_home);
//            Intent i=new Intent(getApplicationContext(), logsignup.class);
//            startActivity(i);
        }
//        if (savedInstanceState ==) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new personalFragment()).commit();
//            navigationview.setCheckedItem(R.id.nav_home);
//            Intent i=new Intent(getApplicationContext(), MainActivity.class);
//            startActivity(i);
//        }

        replaceFragment(new profileFragment(),pno);

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
                replaceFragment(new profileFragment(),pno);
            }
            else if(item.getItemId() == R.id.asset)
            {
                Intent asset=new Intent(getApplicationContext(), assetpage.class);
                asset.putExtra("phoneno",pno);
                startActivity(asset);
            }
            return true;
        });

    }
    private void replaceFragment(Fragment fragment, String pno) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Pass data to the fragment using a Bundle
        Bundle bundle = new Bundle();
        bundle.putString("phoneno", pno);
        fragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}