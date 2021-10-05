package com.knights.hotelmanagementsystem;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class hallsbooking extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    DatabaseReference dbRef;
    EditText booking, timed, emailAddress, fullName, phone, noOfPeople, noOfHours;
    Button booknb;
    Spinner halllist;
    hall_model hall_model;
    String phoneNo;
    String emailAdd;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hallsbooking);

        booking = findViewById(R.id.booking);
        timed = findViewById(R.id.timed);
        emailAddress = findViewById(R.id.emailAddress);
        fullName = findViewById(R.id.fullName);
        phone = findViewById(R.id.phone);
        noOfPeople = findViewById(R.id.noOfPeople);
        booknb = findViewById(R.id.bookBtn);
        halllist = findViewById(R.id.halllist);
        noOfHours = findViewById(R.id.noOfHours);

        hall_model = new hall_model();

        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        timed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        booknb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Halls");
                phoneNo = phone.getText().toString();
                emailAdd = emailAddress.getText().toString().trim();
                try {
                    if (TextUtils.isEmpty(noOfPeople.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter No. of People", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(booking.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter Booking", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(timed.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter Time", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(fullName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter FullName", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(emailAddress.getText().toString()) || !emailAdd.matches(emailPattern))
                        Toast.makeText(getApplicationContext(), "Please enter EmailAddress", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(phone.getText().toString()) || phoneNo.length() != 10)
                        Toast.makeText(getApplicationContext(), "Please enter valid Phone Number", Toast.LENGTH_SHORT).show();
                    else {
                        hall_model.setNumpeople(noOfPeople.getText().toString().trim());
                        hall_model.setDate(booking.getText().toString().trim());
                        hall_model.setTime(timed.getText().toString().trim());
                        hall_model.setHallType(halllist.getSelectedItem().toString().trim());
                        hall_model.setEmail(emailAddress.getText().toString().trim());
                        hall_model.setFullName(fullName.getText().toString().trim());
                        hall_model.setPhone(phone.getText().toString().trim());
                        hall_model.setNoOfHours(Integer.parseInt(noOfHours.getText().toString().trim()));

                        total = totPriceCalculation(halllist.getSelectedItem().toString(), Integer.parseInt(noOfHours.getText().toString()));
                        hall_model.setTotal(total);

                        //Insert into the data base
                        dbRef.push().setValue(hall_model);
                        dbRef.child("lastHallBooking").setValue(hall_model);
                        //feedback to the user via a Toast
                        Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(hallsbooking.this, bookedhallsdply.class);
                        startActivity(intent);


                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid contact number", Toast.LENGTH_SHORT).show();

                }



            }
        });

        ArrayAdapter<String> radp = new ArrayAdapter<String>(hallsbooking.this,
                android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.halllist));
        radp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        halllist.setAdapter(radp);
    }

    public int totPriceCalculation(String hallType, int hours){
        if(hallType.equals("Indcoor Wedding Hall")){
            total = 8000 * hours;
        }else if(hallType.equals("Outdoor Wedding Hall")){
            total = 5000 * hours;
        }else if(hallType.equals("Conference Hall")){
            total = 10000 * hours;
        }else if(hallType.equals("Kids Party Hall")){
            total = 3500 * hours;
        }

        return total;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        String currentDate = DateFormat.getDateInstance().format(c.getTime());

        EditText date_picker = findViewById(R.id.booking);
        date_picker.setText(currentDate);
    }


    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        EditText time_picker = findViewById(R.id.timed);
        time_picker.setText(hourOfDay + ":" + minute);
    }

}