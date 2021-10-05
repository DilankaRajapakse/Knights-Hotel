package com.knights.hotelmanagementsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

public class AdminHalls extends AppCompatActivity {
    RecyclerView hallrecyclerView;
    HallRecyclerViewAdapter hallrecyclerViewAdapter;
    List<hall_model> hallDataList;
    DatabaseReference halldbRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_halls);

        hallrecyclerView = findViewById(R.id.hallrecyclerView);
        hallrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        hallDataList = new ArrayList<>();

        halldbRef = FirebaseDatabase.getInstance().getReference("Halls");
        halldbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if (Objects.equals(data.getKey(), "lastHallBooking")) {
                        break;
                    } else {
                        hall_model hm = data.getValue(hall_model.class);
                        hallDataList.add(hm);
                    }
                    Log.d("rm", String.valueOf(data.hasChild("lastHallBooking")));
                    hallrecyclerViewAdapter = new HallRecyclerViewAdapter(hallDataList);
                    hallrecyclerView.setAdapter(hallrecyclerViewAdapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}