package com.example.netsettle;

import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.LinearLayout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class assetpage extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    LinearLayout cat1;
    LinearLayout cat2;
    LinearLayout cat3;
    LinearLayout cat4;
String pno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asset_page);
        pno = getIntent().getStringExtra("phoneno");
////
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
                replaceFragment(new profileFragment(),pno);
                Intent personal=new Intent(getApplicationContext(), MainActivity.class);
                personal.putExtra("phoneno",pno);
                startActivity(personal);

            }

            return true;
        });


        cat1 = findViewById(R.id.cat1);
        cat2 = findViewById(R.id.cat2);
        cat3 = findViewById(R.id.cat3);
        cat4 = findViewById(R.id.cat4);


        cat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(assetpage.this, ornament.class);
                i.putExtra("phoneno",pno);
                startActivity(i);

            }
        });


        cat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(assetpage.this, realestate.class);
                i.putExtra("phoneno",pno);
                startActivity(i);

            }
        });


        cat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(assetpage.this, vehicle.class);
                i.putExtra("phoneno",pno);
                startActivity(i);

            }
        });


        cat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(assetpage.this, money.class);
                i.putExtra("phoneno",pno);
                startActivity(i);

            }
        });
    }
    private void replaceFragment(Fragment fragment, String pno) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putString("phoneno", pno);
        fragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    public void searchasset(View view) {
        Intent i = new Intent(assetpage.this, SearchAsset.class);
        i.putExtra("phoneno",pno);
        startActivity(i);
    }
}
