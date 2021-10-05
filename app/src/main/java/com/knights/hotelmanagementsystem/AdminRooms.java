package com.knights.hotelmanagementsystem;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdminRooms extends AppCompatActivity {
    RecyclerView recyclerView;
    RoomRecyclerViewAdapter recyclerViewAdapter;
    List<room_model> roomDataList;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_rooms);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        roomDataList = new ArrayList<>();

        dbRef = FirebaseDatabase.getInstance().getReference("Rooms");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if (Objects.equals(data.getKey(), "lastRoomData")) {
                        break;
                    } else {
                        room_model rm = data.getValue(room_model.class);
                        roomDataList.add(rm);
                    }
                    Log.d("rm", String.valueOf(data.hasChild("lastRoomData")));
                    recyclerViewAdapter = new RoomRecyclerViewAdapter(roomDataList);
                    recyclerView.setAdapter(recyclerViewAdapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
