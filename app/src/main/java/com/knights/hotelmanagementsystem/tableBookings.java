package com.knights.hotelmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class tableBookings extends AppCompatActivity {
    TextView fname, lname, email, phone, noOfPeople, date, time, comments, noOfHours, total;
    DatabaseReference dbRef;
    String lastKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_bookings);

        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        noOfPeople = findViewById(R.id.noOfPeople);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        comments = findViewById(R.id.comment);
        noOfHours = findViewById(R.id.noOfHours);
        total = findViewById(R.id.total);


        Button tblConBtn = findViewById(R.id.tblConBtn);
        Button tblDltBtn = findViewById(R.id.tblDltBtn);
        Button updtBookBtn = findViewById(R.id.updtBookBtn);

        tblConBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tableBookings.this, MainActivity.class);
                startActivity(intent);
            }
        });

        tblDltBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Tables");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<String> tableDataKeys = new ArrayList<>();
                        for(DataSnapshot data : dataSnapshot.getChildren()){
                            tableDataKeys.add(data.getKey());
                        }
                        lastKey = tableDataKeys.get(tableDataKeys.size()-2);

                        if(dataSnapshot.hasChild(lastKey)){
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Tables").child(lastKey);
                            dbRef.removeValue();
                        }

                        if(dataSnapshot.hasChild("tableData")){
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Tables").child("tableData");
                            dbRef.removeValue();
                            Toast.makeText(getApplicationContext(), "Booking Deleted Successfully", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "No data to delete.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                Intent intent = new Intent(tableBookings.this, MainActivity.class);
                startActivity(intent);
            }
        });

        updtBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tableBookings.this, tableUpdate.class);
                startActivity(intent);


            }
        });

        dbRef = FirebaseDatabase.getInstance().getReference().child("Tables").child("tableData");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    Log.d("tableTag", String.valueOf(dataSnapshot));
                    fname.setText(dataSnapshot.child("fname").getValue().toString());
                    lname.setText(dataSnapshot.child("lname").getValue().toString());
                    email.setText(dataSnapshot.child("email").getValue().toString());
                    phone.setText(dataSnapshot.child("phone").getValue().toString());
                    noOfPeople.setText(dataSnapshot.child("noOfPeople").getValue().toString());
                    noOfHours.setText(dataSnapshot.child("noOfHours").getValue().toString());
                    date.setText(dataSnapshot.child("date").getValue().toString());
                    time.setText(dataSnapshot.child("time").getValue().toString());
                    comments.setText(dataSnapshot.child("comments").getValue().toString());
                    total.setText(dataSnapshot.child("total").getValue().toString());
                }else{
                    Toast.makeText(getApplicationContext(), "ERROR: No data to display", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}