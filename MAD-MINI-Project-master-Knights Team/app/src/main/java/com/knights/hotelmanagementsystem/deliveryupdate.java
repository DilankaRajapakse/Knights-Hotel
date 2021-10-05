package com.knights.hotelmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class deliveryupdate extends AppCompatActivity {

    Spinner foditm;
    Spinner ncity;
    Button updtodrBtn;
    delivery_model delimodel;
    EditText name,email,phone,quantity,address;
    DatabaseReference dbref;
    int totalprice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliveryupdate);

        updtodrBtn  =findViewById(R.id.updtodrBtn);
        name        = findViewById(R.id.deliuedittxtName);
        email       = findViewById(R.id.deliuedittxtEmail);
        phone       = findViewById(R.id.deliuedittxtPhone);
        quantity    = findViewById(R.id.deliuedittxtQuantity);
        address     = findViewById(R.id.deliuedittxtAddress);
        foditm      =findViewById(R.id.deliufooditemlist);
        ncity       =findViewById(R.id.deliunearcitylist);


        delimodel = new delivery_model();

        //foditm = findViewById(R.id.fooditemlist);
        final ArrayAdapter<String> fditm = new ArrayAdapter<>(deliveryupdate.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.fooditem));
        fditm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foditm.setAdapter(fditm);

         //ncity = findViewById(R.id.nearcitylist);
        final ArrayAdapter<String> ncty = new ArrayAdapter<>(deliveryupdate.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.nearcity));
        ncty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ncity.setAdapter(ncty);

        dbref = FirebaseDatabase.getInstance().getReference().child("Delivery").child("lastDeliveryData");

        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot delisnapshot) {

                if (delisnapshot.hasChildren()) {
                    Log.d("deliveryreadforupdate", String.valueOf(delisnapshot));
                    name.setText(delisnapshot.child("name").getValue().toString());
                    email.setText(delisnapshot.child("email").getValue().toString());
                    phone.setText(delisnapshot.child("phone").getValue().toString());
                    quantity.setText(delisnapshot.child("quantity").getValue().toString());
                    address.setText(delisnapshot.child("address").getValue().toString());
                    foditm.setSelection(fditm.getPosition(delisnapshot.child("fooditm").getValue().toString()));
                    ncity.setSelection(ncty.getPosition(delisnapshot.child("city").getValue().toString()));



                } else {
                    Toast.makeText(getApplicationContext(), "No source to Display", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        updtodrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference upref = FirebaseDatabase.getInstance().getReference().child("Delivery");
                upref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<String> arrKeys = new ArrayList();
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            Log.d("yyy", data.getKey());
                            arrKeys.add(data.getKey());
//
                        }
                        String lastKey = arrKeys.get(arrKeys.size() - 2);


                        if (dataSnapshot.hasChild(lastKey)){

                            try {
                               /* if(TextUtils.isEmpty(name.getText().toString()))
                                    Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
                                else if(TextUtils.isEmpty(email.getText().toString()))
                                    Toast.makeText(getApplicationContext(), "Please enter your email", Toast.LENGTH_SHORT).show();
                                else if(TextUtils.isEmpty(foditm.getSelectedItem().toString()))
                                    Toast.makeText(getApplicationContext(), "Please select a food item", Toast.LENGTH_SHORT).show();
                                else if(TextUtils.isEmpty(phone.getText().toString()))
                                    Toast.makeText(getApplicationContext(), "Please enter your phone number", Toast.LENGTH_SHORT).show();
                                else if(TextUtils.isEmpty(quantity.getText().toString()))
                                    Toast.makeText(getApplicationContext(), "Please enter quantity", Toast.LENGTH_SHORT).show();
                                else if(TextUtils.isEmpty(address.getText().toString()))
                                    Toast.makeText(getApplicationContext(), "Please enter a delivery address", Toast.LENGTH_SHORT).show();
                                    //else if(TextUtils.isEmpty(ncity.getSelectedItem().toString()))
                                    //Toast.makeText(getApplicationContext(), "Please select a near city", Toast.LENGTH_SHORT).show();
                                else {*/
                                        if (foditm.getSelectedItem().toString().equals("Rice And Curry")) {
                                            totalprice = 250 * Integer.parseInt(quantity.getText().toString());
                                        } else if (foditm.getSelectedItem().toString().equals("Fried Rice")) {
                                            totalprice = 350 * Integer.parseInt(quantity.getText().toString());
                                        } else if (foditm.getSelectedItem().toString().equals("Koththu")) {
                                            totalprice = 400 * Integer.parseInt(quantity.getText().toString());
                                        } else if (foditm.getSelectedItem().toString().equals("Hoppers")) {
                                            totalprice = 30 * Integer.parseInt(quantity.getText().toString());
                                        }
                                    /*}*/
                                delimodel.setName(name.getText().toString().trim());
                                delimodel.setEmail(email.getText().toString().trim());
                                delimodel.setFooditm(foditm.getSelectedItem().toString().trim());
                                delimodel.setPhone(phone.getText().toString().trim());
                                delimodel.setQuantity(quantity.getText().toString().trim());
                                delimodel.setAddress(address.getText().toString().trim());
                                delimodel.setCity(ncity.getSelectedItem().toString().trim());
                                delimodel.setTotalprice(totalprice);

                                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Delivery").child(lastKey);
                                dbref.setValue(delimodel);


                                Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();

                            }catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();
                        }
                        if(dataSnapshot.hasChild("lastDeliveryData")){
                            try{
                                if (foditm.getSelectedItem().toString().equals("Rice And Curry")) {
                                    totalprice = 250 * Integer.parseInt(quantity.getText().toString());
                                } else if (foditm.getSelectedItem().toString().equals("Fried Rice")) {
                                    totalprice = 350 * Integer.parseInt(quantity.getText().toString());
                                } else if (foditm.getSelectedItem().toString().equals("Koththu")) {
                                    totalprice = 400 * Integer.parseInt(quantity.getText().toString());
                                } else if (foditm.getSelectedItem().toString().equals("Hoppers")) {
                                    totalprice = 30 * Integer.parseInt(quantity.getText().toString());
                                }
                                delimodel.setName(name.getText().toString().trim());
                                delimodel.setEmail(email.getText().toString().trim());
                                delimodel.setFooditm(foditm.getSelectedItem().toString().trim());
                                delimodel.setPhone(phone.getText().toString().trim());
                                delimodel.setQuantity(quantity.getText().toString().trim());
                                delimodel.setAddress(address.getText().toString().trim());
                                delimodel.setCity(ncity.getSelectedItem().toString().trim());
                                delimodel.setTotalprice(totalprice);

                                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Delivery").child("lastDeliveryData");
                                dbRef.setValue(delimodel);

                                Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(deliveryupdate.this, deliveryconfirm.class);
                                startActivity(intent);

                            }catch (NumberFormatException e){
                                Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                            }


                        }
                        else{
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


}