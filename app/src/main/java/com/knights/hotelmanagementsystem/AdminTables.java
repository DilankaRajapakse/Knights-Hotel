package com.knights.hotelmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdminTables extends AppCompatActivity {
    RecyclerView recyclerView;
    TableRecyclerViewAdapter tableRecyclerViewAdapter;
    List<tables_model> tableBookingDataList;
    DatabaseReference tbldbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tables);

        recyclerView = findViewById(R.id.tablerecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tableBookingDataList = new ArrayList<>();

        tbldbRef = FirebaseDatabase.getInstance().getReference("Tables");
        tbldbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if (Objects.equals(data.getKey(), "tableData")) {
                        break;
                    } else {
                        tables_model tm = data.getValue(tables_model.class);
                        tableBookingDataList.add(tm);
                    }
                    Log.d("tm", String.valueOf(data.hasChild("lastHallBooking")));
                    tableRecyclerViewAdapter = new TableRecyclerViewAdapter(tableBookingDataList);
                    recyclerView.setAdapter(tableRecyclerViewAdapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}