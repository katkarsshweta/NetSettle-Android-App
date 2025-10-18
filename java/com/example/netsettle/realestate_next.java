package com.example.netsettle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.Calendar;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class realestate_next extends AppCompatActivity {
    String pno;
    String selectedDate;
    String selectedTime;
    //String selectedCategoryMode;
    EditText name;
    String vname;
    String vtype;
    String vquantity;
    String vloc;
    String vremark;
    EditText quantity;
    EditText loc;
    EditText remark;
    ActivityResultLauncher<String> getContent ;
    Button selectFileButton;
    FirebaseStorage storage;
    StorageReference storageRef;
    FirebaseDatabase database;
    DatabaseReference usersRef;
    String downloadUrl;
    @SuppressLint("MissingInflatedId")
    Button selectDateButton, selectTimeButton;
    int year, month, day, hour, minute;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realestate_next);

        selectFileButton = findViewById(R.id.b3);
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        database = FirebaseDatabase.getInstance();
        usersRef = database.getReference().child("users");

        pno=getIntent().getStringExtra("phoneno");


        selectDateButton = findViewById(R.id.selectDateButton);
        selectTimeButton = findViewById(R.id.selectTimeButton);

        getContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri != null) {
                        String extension = getFileExtensionFromUri(getApplicationContext(), uri);;

                        // Create a reference to the storage location where the file will be uploaded
                        StorageReference fileRef = storageRef.child("files/" + System.currentTimeMillis() +extension);

                        // Upload file to Firebase Storage
                        fileRef.putFile(uri)
                                .addOnSuccessListener(taskSnapshot -> {
                                    // Get the download URL of the uploaded file
                                    fileRef.getDownloadUrl().addOnSuccessListener(downloadUri -> {
                                        // Save the download URL to Firebase Realtime Database or perform other operations as needed
                                        downloadUrl = downloadUri.toString();
                                        DatabaseReference currentUserExtraRef = usersRef.child(pno).child("asset").child("real_estate");

                                        currentUserExtraRef.child(vname).child("document").setValue(downloadUrl);
//                                                .addOnCompleteListener(task -> {
//                                            if (task.isSuccessful()) {
//                                                // URL stored successfully
//                                            } else {
//                                                // Handle failure
//                                            }
//                                        });
                                        // Save this URL to Firebase Realtime Database under the user's node or any relevant location
                                    });
                                });
//                                .addOnFailureListener(e -> {
//                                    // Handle any errors during upload
//                                });
                    }
                });

        selectFileButton.setOnClickListener(view -> {
            getContent.launch("*/*");
        });

        // Get the current date and time
        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        // Set a click listener on the date button to show the DatePickerDialog
        selectDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(realestate_next.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // Set the selected date to your variables
                                realestate_next.this.year = year;
                                month = monthOfYear;
                                day = dayOfMonth;

                                // Display the selected date
                                selectedDate = day + "/" + (month + 1) + "/" + year;
                                selectDateButton.setText(selectedDate);
                            }
                        }, year, month, day);

                datePickerDialog.show();
            }
        });

        // Set a click listener on the time button to show the TimePickerDialog
        selectTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(realestate_next.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
                                // Set the selected time to your variables
                                hour = hourOfDay;
                                realestate_next.this.minute = minute;

                                // Display the selected time
                                selectedTime = String.format("%02d:%02d", hour, minute);
                                selectTimeButton.setText(selectedTime);
                            }
                        }, hour, minute, true);

                timePickerDialog.show();
            }
        });


        Spinner categorySpinner = findViewById(R.id.typespinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                this,
                R.array.realestate_type,
                android.R.layout.simple_spinner_item
        );

        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        categorySpinner.setAdapter(adapter1);

        // Set a listener to handle the selected item
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Get the selected category mode
                vtype = parentView.getItemAtPosition(position).toString();

                // Do something with the selected category mode
                // For example, display it in a Toast message
                Toast.makeText(getApplicationContext(), "Selected: " + vtype, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Another interface callback
            }
        });
    }
    private String getFileExtensionFromUri(Context appContext, Uri uri) {
        ContentResolver contentResolver = appContext.getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    public void add(View view) {
        name=findViewById(R.id.nameEditText);
        quantity=findViewById(R.id.priceEditText);
        loc=findViewById(R.id.locationEditText);
        remark=findViewById(R.id.remarkEditText);

        vname=name.getText().toString();
        vquantity=quantity.getText().toString();
        vloc=loc.getText().toString();
        vremark=remark.getText().toString();
        DatabaseReference currentUserExtraRef = usersRef.child(pno).child("asset").child("real_estate");

        currentUserExtraRef.child(vname).child("document").setValue(downloadUrl);
        currentUserExtraRef.child(vname).child("Type").setValue(vtype);
        currentUserExtraRef.child(vname).child("Quantity").setValue(vquantity);
        currentUserExtraRef.child(vname).child("Date").setValue(selectedDate);
        currentUserExtraRef.child(vname).child("Time").setValue(selectedTime);
        currentUserExtraRef.child(vname).child("Location").setValue(vloc);
        currentUserExtraRef.child(vname).child("Remark").setValue(vremark);
        Intent i = new Intent(realestate_next.this,realestate.class);
        startActivity(i);
    }
    public void cancel(View view) {
        Intent i = new Intent(realestate_next.this,realestate.class);
        startActivity(i);
    }
}
