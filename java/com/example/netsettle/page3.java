package com.example.netsettle;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Locale;



public class page3 extends AppCompatActivity {
    private EditText amount1EditText;
    private DatePickerDialog datePickerDialog;
    private Button timeButton;
    private Button dateButton;
    int hour,minute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);
        amount1EditText = findViewById(R.id.amount1EditText);


        initDatePicker();
        dateButton = findViewById(R.id.selectDateButton);
        dateButton.setText(getTodaysDate());
        timeButton = findViewById(R.id.TimePicker);
    }




    // Handle the "Save" button click
    public void onSaveButton1Click(View view) {
        // Get the entered amount
        String amountText = amount1EditText.getText().toString().trim();


        if (!amountText.isEmpty()) {
            int amount = Integer.parseInt(amountText);
            if (amount >= 0) {
                // Pass the amount back to Page2 and finish this activity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("amount", amount);
                setResult(RESULT_OK, resultIntent);
                finish();
            } else {
                // Display an error message or take appropriate action
                // (e.g., show a Toast or setError on the EditText)
            }
        } else {
            // Display an error message for empty input
            // (e.g., show a Toast or setError on the EditText)
        }
    }


    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month,year);
    }




    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setTag(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);


        int style = AlertDialog.THEME_HOLO_LIGHT;


        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);
    }


    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }


    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";


        //Default should never happen
        return "JAN";
    }


    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }


    public void TimePicker(View view)
    {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute)
            {
                hour=hourOfDay;
                minute=minute;
                timeButton.setText(String.format(Locale.getDefault(),"%02d:%02d",hourOfDay,minute));
            }
        };
        boolean is24HourView = true;
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,onTimeSetListener,hour,minute,is24HourView);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();


        Spinner paymentModeSpinner = findViewById(R.id.spinner3);


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.payment_modes,
                android.R.layout.simple_spinner_item
        );


        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        // Apply the adapter to the spinner
        paymentModeSpinner.setAdapter(adapter);


        // Set a listener to handle the selected item
        paymentModeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Get the selected payment mode
                String selectedPaymentMode = parentView.getItemAtPosition(position).toString();


                // Do something with the selected payment mode
                // For example, you can display it in a Toast message
                Toast.makeText(getApplicationContext(), "Selected: " + selectedPaymentMode, Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Another interface callback
            }
        });


        Spinner categoryspinner = findViewById(R.id.spinner2);


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                this,
                R.array.category,
                android.R.layout.simple_spinner_item
        );


        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        // Apply the adapter to the spinner
        categoryspinner.setAdapter(adapter1);


        // Set a listener to handle the selected item
        categoryspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Get the selected payment mode
                String selectedcategoryMode = parentView.getItemAtPosition(position).toString();


                // Do something with the selected payment mode
                // For example, you can display it in a Toast message
                Toast.makeText(getApplicationContext(), "Selected: " + selectedcategoryMode, Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Another interface callback
            }
        });
    }
}
