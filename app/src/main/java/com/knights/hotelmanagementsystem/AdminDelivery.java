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

public class AdminDelivery extends AppCompatActivity {
    RecyclerView delvrecyclerView;
    DeliveryRecyclerViewAdapter deliveryRecyclerViewAdapter;
    List<delivery_model> deliveryDataList;
    DatabaseReference delvdbRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delivery);

        delvrecyclerView = findViewById(R.id.delvrecyclerView);
        delvrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        deliveryDataList = new ArrayList<>();

        delvdbRef = FirebaseDatabase.getInstance().getReference("Delivery");
        delvdbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if (Objects.equals(data.getKey(), "lastDeliveryData")) {
                        break;
                    } else {
                        delivery_model dm = data.getValue(delivery_model.class);
                        deliveryDataList.add(dm);
                    }
                    Log.d("dm", String.valueOf(data.hasChild("lastDeliveryData")));
                    deliveryRecyclerViewAdapter = new DeliveryRecyclerViewAdapter(deliveryDataList);
                    delvrecyclerView.setAdapter(deliveryRecyclerViewAdapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}