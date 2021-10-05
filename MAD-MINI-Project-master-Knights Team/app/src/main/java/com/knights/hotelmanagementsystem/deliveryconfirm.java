package com.knights.hotelmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class deliveryconfirm extends AppCompatActivity {
    Button updateBtn;
    Button deleteBtn;
    Button confirmBtn;

    TextView name,email,phone,quantity,address,fooditm,ncity,total;
    DatabaseReference dbref;
    String lastKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliveryconfirm);

        name = findViewById(R.id.cfs_name);
        email = findViewById(R.id.cfs_email);
        phone = findViewById(R.id.cfs_phone);
        quantity = findViewById(R.id.cfs_quantity);
        address = findViewById(R.id.cfs_deliveryaddress);
        fooditm = findViewById(R.id.cfs_fooditem);
        ncity = findViewById(R.id.cfs_nearcity);
        total = findViewById(R.id.cfs_totalprice);

        updateBtn = (Button) findViewById(R.id.deliodrupdtBtn);
        deleteBtn = (Button) findViewById(R.id.deliodrdelBtn);
        confirmBtn = (Button) findViewById(R.id.deliodrcfBtn);

        dbref = FirebaseDatabase.getInstance().getReference().child("Delivery").child("lastDeliveryData");

        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    Log.d("deliveryread", String.valueOf(dataSnapshot));
                    name.setText(dataSnapshot.child("name").getValue().toString());
                    email.setText(dataSnapshot.child("email").getValue().toString());
                    phone.setText(dataSnapshot.child("phone").getValue().toString());
                    quantity.setText(dataSnapshot.child("quantity").getValue().toString());
                    address.setText(dataSnapshot.child("address").getValue().toString());
                    fooditm.setText(dataSnapshot.child("fooditm").getValue().toString());
                    ncity.setText(dataSnapshot.child("city").getValue().toString());
                    total.setText(dataSnapshot.child("totalprice").getValue().toString());

                } else {
                    Toast.makeText(getApplicationContext(), "No source to Display", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent update = new Intent(deliveryconfirm.this,deliveryupdate.class);
                startActivity(update);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Delivery");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<String> tableDataKeys = new ArrayList<>();
                        for(DataSnapshot data : dataSnapshot.getChildren()){
                            tableDataKeys.add(data.getKey());
                        }
                        lastKey = tableDataKeys.get(tableDataKeys.size()-2);

                        if(dataSnapshot.hasChild(lastKey)){
                            dbref = FirebaseDatabase.getInstance().getReference().child("Delivery").child(lastKey);
                            dbref.removeValue();
                        }

                        if(dataSnapshot.hasChild("lastDeliveryData")){
                            dbref = FirebaseDatabase.getInstance().getReference().child("Delivery").child("lastDeliveryData");
                            dbref.removeValue();
                            Toast.makeText(getApplicationContext(), "Order Deleted Successfully", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "No data to delete.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                Intent delete = new Intent(deliveryconfirm.this,MainActivity.class);
                startActivity(delete);
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent conf = new Intent(deliveryconfirm.this,MainActivity.class);
                startActivity(conf);
            }
        });
    }
}
