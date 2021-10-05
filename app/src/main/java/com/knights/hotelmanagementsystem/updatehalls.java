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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class updatehalls extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    Spinner spinnerhty;
    EditText updtedate, utimet, updname, upemail, updphone, upnoofPeople, noOfHours;
    Button updabutton;
    DatabaseReference dbRef;
    hall_model hallModel;
    int total;
    String phoneNo;
    String emailAdd;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatehalls);

        spinnerhty = findViewById(R.id.spinnerhty);
        updtedate = findViewById(R.id.updtedate);
        utimet = findViewById(R.id.utimet);
        updname = findViewById(R.id.updname);
        upemail = findViewById(R.id.upemail);
        updphone = findViewById(R.id.updphone);
        upnoofPeople = findViewById(R.id.noOfPeople);
        noOfHours = findViewById(R.id.noOfHours);

        updabutton = findViewById(R.id.updabutton);

        updtedate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        utimet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        hallModel = new hall_model();


        final ArrayAdapter<String> radp = new ArrayAdapter<String>(updatehalls.this,
                android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.halllist));
        radp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerhty.setAdapter(radp);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Halls").child("lastHallBooking");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    spinnerhty.setSelection(radp.getPosition(dataSnapshot.child("hallType").getValue().toString()));
                    updtedate.setText(dataSnapshot.child("date").getValue().toString());
                    utimet.setText(dataSnapshot.child("time").getValue().toString());
                    updname.setText(dataSnapshot.child("fullName").getValue().toString());
                    upemail.setText(dataSnapshot.child("email").getValue().toString());
                    updphone.setText(dataSnapshot.child("phone").getValue().toString());
                    upnoofPeople.setText(dataSnapshot.child("numpeople").getValue().toString());
                    noOfHours.setText(dataSnapshot.child("noOfHours").getValue().toString());

                    if (spinnerhty.getSelectedItem().toString().equals("Indoor Wedding Hall")) {
                        total = 8000 * Integer.parseInt(noOfHours.getText().toString());
                    } else if (spinnerhty.getSelectedItem().toString().equals("Outdoor Wedding Hall")) {
                        total = 5000 * Integer.parseInt(noOfHours.getText().toString());
                    } else if (spinnerhty.getSelectedItem().toString().equals("Conference Hall")) {
                        total = 10000 * Integer.parseInt(noOfHours.getText().toString());
                    } else if (spinnerhty.getSelectedItem().toString().equals("Kids Party Hall")) {
                        total = 3500 * Integer.parseInt(noOfHours.getText().toString());
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        updabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneNo = updphone.getText().toString();
                emailAdd = upemail.getText().toString().trim();
                DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("Halls");
                upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<String> arrKeys = new ArrayList();
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            arrKeys.add(data.getKey());
                        }
                        String lastKey = arrKeys.get(arrKeys.size() - 2);

                        if (dataSnapshot.hasChild(lastKey)) {
                            try {
                                if (TextUtils.isEmpty(upnoofPeople.getText().toString()))
                                    Toast.makeText(getApplicationContext(), "Please enter No. of People", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(spinnerhty.getSelectedItem().toString()))
                                    Toast.makeText(getApplicationContext(), "Please enter Booking", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(utimet.getText().toString()))
                                    Toast.makeText(getApplicationContext(), "Please enter Time", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(upemail.getText().toString()) || !emailAdd.matches(emailPattern))
                                    Toast.makeText(getApplicationContext(), "Please check EmailAddress", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(updname.getText().toString()))
                                    Toast.makeText(getApplicationContext(), "Please enter FullName", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(updphone.getText().toString()) || phoneNo.length() != 10)
                                    Toast.makeText(getApplicationContext(), "Please enter check Phone Number", Toast.LENGTH_SHORT).show();
                                else {
                                    if (spinnerhty.getSelectedItem().toString().equals("Indoor Wedding Hall")) {
                                        total = 8000 * Integer.parseInt(noOfHours.getText().toString());
                                    } else if (spinnerhty.getSelectedItem().toString().equals("Outdoor Wedding Hall")) {
                                        total = 5000 * Integer.parseInt(noOfHours.getText().toString());
                                    } else if (spinnerhty.getSelectedItem().toString().equals("Conference Hall")) {
                                        total = 10000 * Integer.parseInt(noOfHours.getText().toString());
                                    } else if (spinnerhty.getSelectedItem().toString().equals("Kids Party Hall")) {
                                        total = 3500 * Integer.parseInt(noOfHours.getText().toString());
                                    }

                                    hallModel.setHallType(spinnerhty.getSelectedItem().toString().trim());
                                    hallModel.setDate(updtedate.getText().toString().trim());
                                    hallModel.setTime(utimet.getText().toString().trim());
                                    hallModel.setEmail(upemail.getText().toString().trim());
                                    hallModel.setPhone(updphone.getText().toString().trim());
                                    hallModel.setFullName(updname.getText().toString().trim());
                                    hallModel.setEmail(upemail.getText().toString().trim());
                                    hallModel.setNumpeople(upnoofPeople.getText().toString().trim());
                                    hallModel.setNoOfHours(Integer.parseInt(noOfHours.getText().toString().trim()));

                                    hallModel.setTotal(total);

                                    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Halls").child(lastKey);
                                    dbRef.setValue(hallModel);
                                }

                                //Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();

                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Error! Check Again", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();
                        }

                        if (dataSnapshot.hasChild("lastHallBooking")) {
                            try {
                                if (TextUtils.isEmpty(upnoofPeople.getText().toString()))
                                    Toast.makeText(getApplicationContext(), "Please enter No. of People", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(spinnerhty.getSelectedItem().toString()))
                                    Toast.makeText(getApplicationContext(), "Please enter Booking", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(utimet.getText().toString()))
                                    Toast.makeText(getApplicationContext(), "Please enter Time", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(updname.getText().toString()))
                                    Toast.makeText(getApplicationContext(), "Please enter FullName", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(upemail.getText().toString()) || !emailAdd.matches(emailPattern))
                                    Toast.makeText(getApplicationContext(), "Please check EmailAddress", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(updphone.getText().toString()) || phoneNo.length() != 10)
                                    Toast.makeText(getApplicationContext(), "Please enter check Phone Number", Toast.LENGTH_SHORT).show();
                                else {
                                    hallModel.setHallType(spinnerhty.getSelectedItem().toString().trim());
                                    hallModel.setDate(updtedate.getText().toString().trim());
                                    hallModel.setTime(utimet.getText().toString().trim());
                                    hallModel.setEmail(upemail.getText().toString().trim());
                                    hallModel.setPhone(updphone.getText().toString().trim());
                                    hallModel.setFullName(updname.getText().toString().trim());
                                    hallModel.setEmail(upemail.getText().toString().trim());
                                    hallModel.setNumpeople(upnoofPeople.getText().toString().trim());
                                    hallModel.setNoOfHours(Integer.parseInt(noOfHours.getText().toString().trim()));
                                    total = totPriceCalculation(spinnerhty.getSelectedItem().toString(), Integer.parseInt(noOfHours.getText().toString()));

                                    hallModel.setTotal(total);
                                    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Halls").child("lastHallBooking");
                                    dbRef.setValue(hallModel);

                                    Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();


                                    Intent intent = new Intent(updatehalls.this, bookedhallsdply.class);
                                    startActivity(intent);
                                }
                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Error! Check Again", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

    }

    public int totPriceCalculation(String hallType, int hours) {
        if (hallType.equals("Indoor Wedding Hall")) {
            total = 8000 * hours;
        } else if (hallType.equals("Outdoor Wedding Hall")) {
            total = 5000 * hours;
        } else if (hallType.equals("Conference Hall")) {
            total = 10000 * hours;
        } else if (hallType.equals("Kids Party Hall")) {
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