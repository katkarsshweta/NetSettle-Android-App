package com.example.netsettle;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ornament extends AppCompatActivity {

    private List<String> informationList;
    private ArrayAdapter<String> adapter;
    private ListView listView;

    private EditText ornament_name;
    private EditText ornament_type;
    private EditText ornament_quantity;
    private EditText ornament_price;
    private Button ornament_datePicker;
    private Button ornament_timeButton;
    private EditText ornament_location;
    private EditText ornament_remark;

    String pno;
    int ono = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ornament);

//        pno = getIntent().getStringExtra("phoneno");
//        ornament_name = findViewById(R.id.nameEditText);
//        ornament_type = findViewById(R.id.typeEditText);
//        ornament_quantity = findViewById(R.id.quantityEditText);
//        ornament_price = findViewById(R.id.priceEditText);
//        ornament_datePicker = findViewById(R.id.selectDateButton);
//        ornament_timeButton = findViewById(R.id.selectTimeButton);
//        ornament_location = findViewById(R.id.locationEditText);
//        ornament_remark = findViewById(R.id.remarkEditText);
         listView = findViewById(R.id.listView);

        // Initialize the informationList
        informationList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, informationList);
        listView.setAdapter(adapter);
    }

    // FloatingActionButton onClick event method
    public void fab_next(View view) {
        pno=getIntent().getStringExtra("phoneno");
         Intent i = new Intent(ornament.this, ornament_next.class);
        i.putExtra("phoneno",pno);
         startActivity(i);
    }
}
